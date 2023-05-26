package error404.gfg.healthcare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class SosActivity extends AppCompatActivity {

    private static final int IGNORE_BATTERY_OPTIMIZATION_REQUEST = 1002;
    private static final int PICK_CONTACT = 1;

    // create instances of various classes to be used
    LinearLayout button1;
    LottieAnimationView l;
    ListView listView;
    DbHelper db;
    List<ContactModel> list;
    Intent sensorServiceIntent; // Declare the Intent variable
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);




        l=(LottieAnimationView) findViewById(R.id.l);

        // check for runtime permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS, Manifest.permission.READ_CONTACTS}, 100);
            }
        }

        // this is a special permission required only by devices using
        // Android Q and above. The Access Background Permission is responsible
        // for populating the dialog with "ALLOW ALL THE TIME" option
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 100);
        }

        // check for BatteryOptimization,
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (pm != null && !pm.isIgnoringBatteryOptimizations(getPackageName())) {
                askIgnoreOptimization();
            }
        }

        // start the service
        SensorService sensorService = new SensorService();
        Intent intent = new Intent(this, sensorService.getClass());
        if (!isMyServiceRunning(sensorService.getClass())) {
            startService(intent);
        }

        // start the service
        sensorServiceIntent = new Intent(this, SensorService.class);
        if (!isMyServiceRunning(SensorService.class)) {
            startService(sensorServiceIntent);
        }


        button1 = findViewById(R.id.Button1);
        listView = (ListView) findViewById(R.id.ListView);
        db = new DbHelper(this);
        list = db.getAllContacts();
        customAdapter = new CustomAdapter(this, list);
        listView.setAdapter(customAdapter);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling of getContacts()
                if (db.count() != 5) {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, PICK_CONTACT);
                } else {
                    Toast.makeText(SosActivity.this, "Can't Add more than 5 Contacts", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //sos switch
        Switch sosSwitch = findViewById(R.id.sosSwitch);
        sosSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                SharedPreferences preferences = getSharedPreferences("SOS_PREFS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("sosEnabled", isChecked);
                editor.apply();




                if (isChecked) {
                    // Start the SOS service

                    l.setMaxFrame(100);
                    startSosService();
                } else {
                    // Stop the SOS service

                    l.setMaxFrame(0);
                    stopSosService();
                }
            }
        });






        // Start or stop the SOS service based on the initial state of the switch
        boolean isSosEnabled = sosSwitch.isChecked();
        if (isSosEnabled) {
            startSosService();
        } else {
            stopSosService();
        }

        //UI STARTED
        //status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bg));

        //status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //animation Background
        LinearLayout bg = findViewById(R.id.bg);
        AnimationDrawable animationDrawable = (AnimationDrawable) bg.getBackground();
        animationDrawable.setEnterFadeDuration(700);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();


    }

    // method to check if the service is running
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("Service status", "Running");
                return true;
            }
        }
        Log.i("Service status", "Not running");
        return false;
    }

    @Override
    protected void onDestroy() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, ReactivateService.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Permissions Denied!\n Can't use the App!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get the contact from the PhoneBook of device
        switch (requestCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {

                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        @SuppressLint("Range") String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String phone = null;
                        try {
                            if (hasPhone.equalsIgnoreCase("1")) {
                                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                                phones.moveToFirst();
                                phone = phones.getString(phones.getColumnIndex("data1"));
                            }
                            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            db.addcontact(new ContactModel(0, name, phone));
                            list = db.getAllContacts();
                            customAdapter.refresh(list);
                        } catch (Exception ex) {
                        }
                    }
                }
                break;
        }
    }

    // this method prompts the user to remove any
    // battery optimisation constraints from the App
    private void askIgnoreOptimization() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            @SuppressLint("BatteryLife") Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, IGNORE_BATTERY_OPTIMIZATION_REQUEST);
        }

    }

    // method to start the SOS service
    private void startSosService() {
        if (!isMyServiceRunning(SensorService.class)) {
            startService(sensorServiceIntent);
        }
    }

    // method to stop the SOS service
    private void stopSosService() {
        if (isMyServiceRunning(SensorService.class)) {
            stopService(sensorServiceIntent);
        }

    }



}
