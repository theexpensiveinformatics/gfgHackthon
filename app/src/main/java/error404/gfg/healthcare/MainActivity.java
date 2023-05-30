package error404.gfg.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.Timer;
import java.util.TimerTask;

import error404.gfg.healthcare.Token.TokenManager;
import error404.gfg.healthcare.model.UserModel;
import error404.gfg.healthcare.reotrfit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_OVERLAY_PERMISSION = 0;
    RetrofitService retrofitService = new RetrofitService();
    error404.gfg.healthcare.reotrfit.userAPI userAPI = retrofitService.getRetrofit().create(error404.gfg.healthcare.reotrfit.userAPI.class);
    TextView textView,Res;
    FirebaseAuth fAuth;
    FirebaseRemoteConfig remoteConfig;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseAuth fAuth;
        fAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser; firebaseUser = fAuth.getCurrentUser();
        String uid;

        name="x12";
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        //if internet connected
        if ((wifi != null & datac != null) && (wifi.isConnected() | datac.isConnected())) {
//            This Code Is for firebase
            //if userAuthenticated
            if (fAuth.getCurrentUser() != null) {

                uid = firebaseUser.getUid();
                //here we have get to name


                DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference NameRef = DBref.child("users").child(uid);
                NameRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String firstName = snapshot.child("FirstName").getValue(String.class);
                        String lastName = snapshot.child("LastName").getValue(String.class);
                        name = firstName + " " + lastName;

                        if(name!="x12")
                        {
                            startActivity(new Intent(getApplicationContext(), home_screen_2.class));
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,""+error,Toast.LENGTH_SHORT).show();
                    }
                });





            } else {
                Intent i = new Intent(MainActivity.this, Authantication.class);
                startActivity(i);
                finish();
            }


            //this code is for API
            //if userAuthenticated
//            TokenManager tokenManager = TokenManager.getInstance(getApplicationContext());
//            String accessToken = tokenManager.getAccessToken();
//            if (accessToken != null) {
//
//                Call<UserModel> call = userAPI.getUserProfile("Bearer " + accessToken);
//                call.enqueue(new Callback<UserModel>() {
//                    @Override
//                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                        if (response.isSuccessful()) {
//                            UserModel userModel = response.body();
//                            String firstName = userModel.getFirstName();
//                            String lastName = userModel.getLastName();
//                            String name = firstName + " " + lastName;
//
//                            if (!"x12".equals(name)) {
//                                startActivity(new Intent(getApplicationContext(), home_screen_2.class));
//                                finish();
//                            }
//                        } else {
//                            // Handle API error
//                            Intent i = new Intent(MainActivity.this, Authantication.class);
//                            startActivity(i);
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserModel> call, Throwable t) {
//                        // Handle API failure
//                        Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            } else {
//                Intent i = new Intent(MainActivity.this, Authantication.class);
//                startActivity(i);
//                finish();
//            }
        }
        else {
            Intent i2 = new Intent(MainActivity.this, NoInternet.class);
            startActivity(i2);
            finish();

        }

//        // Check if the SYSTEM_ALERT_WINDOW permission is granted
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
//            // If not, request the permission
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
//
//            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
//        } else {
//            // Permission already granted or not needed
//            // Add your overlay window code here
//            startService(new Intent(MainActivity.this,FloatingViewService.class));
//        }





        FirebaseMessaging.getInstance().subscribeToTopic("notification");

        // light theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.white));


        textView=(TextView) findViewById(R.id.QMe);

//        setTextViewColor(textView,
//                getResources().getColor(R.color.one),getResources().getColor(R.color.two));




    }

    public void setTextViewColor(TextView textView , int...color)
    {
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("QMe");
        Shader shader = new LinearGradient(0,0,width,textView.getTextSize(),color,null ,Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
    }

    private int getCurrentVersionCode(){

        PackageInfo packageInfo = null;
        try
        {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
        }catch (Exception e)
        {
            Log.d("myApp",e.getMessage());
        }
        return packageInfo.versionCode;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
//                // Permission granted, add your overlay window code here
//            } else {
//                // Permission denied, handle accordingly
//            }
//        }
//    }



}