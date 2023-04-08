package error404.gfg.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PathMeasure;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import error404.gfg.healthcare.databinding.ActivityEcallBinding;


public class ECall extends AppCompatActivity {
    String ECallNum,Name,Relation;
    ActivityEcallBinding activityEcallBinding;
    SharedPreferences sharedPreferencesECall;
    private static final String KEY_NAME= "namekey";
    private static final String KEY_NUMBER= "numkey";
    private static final String KEY_RELATION= "relationkey";
    private int PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecall);
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

        //activityBinding
        activityEcallBinding = ActivityEcallBinding.inflate(getLayoutInflater());
        View view = activityEcallBinding.getRoot();
        setContentView(view);


        //getSharepref
        //getshre
        sharedPreferencesECall= getSharedPreferences(KEY_NUMBER,MODE_PRIVATE);
        sharedPreferencesECall= getSharedPreferences(KEY_NAME,MODE_PRIVATE);
        sharedPreferencesECall= getSharedPreferences(KEY_RELATION,MODE_PRIVATE);

        //checking
        String numkey = sharedPreferencesECall.getString(KEY_NUMBER,null);
        String relationkey = sharedPreferencesECall.getString(KEY_RELATION,null);
        String namekey = sharedPreferencesECall.getString(KEY_NAME,null);

            activityEcallBinding.ifAddedBg.setVisibility(View.GONE);

        //click on add number
        if(numkey!=null)
        {
            activityEcallBinding.NameEdit.setText(namekey);
            activityEcallBinding.RelationEdit.setText(relationkey);
            activityEcallBinding.EcallEdit.setText(numkey);

            activityEcallBinding.setName.setText(namekey);
            activityEcallBinding.setRelation.setText(relationkey);
            activityEcallBinding.setNumber.setText(numkey);

            activityEcallBinding.ifnotaddesBg.setVisibility(View.GONE);
            activityEcallBinding.ifAddedBg.setVisibility(View.VISIBLE);

            //powerbutton
            BroadcastReceiver receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    String s = "tel:" + numkey;
                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse(s));
                    startActivity(call);

                }
            };

            // on below line we are creating an intent filter.
            IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);

            // on below line we are registering receiver.
            registerReceiver(receiver, filter);
        }

        activityEcallBinding.deleteCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferencesECall.edit();
                editor.clear();
                editor.commit();


                Timer _timer = new Timer();

                _Animator(activityEcallBinding.ifAddedBg,"alpha",0,300);


                TimerTask timer = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activityEcallBinding.ifnotaddesBg.setVisibility(View.VISIBLE);
                                activityEcallBinding.ifAddedBg.setVisibility(View.GONE);
                                _Animator(activityEcallBinding.ifnotaddesBg,"alpha",1,300);
                            }
                        });
                    }
                };
                _timer.schedule(timer, (int) (300));

            }
        });

        //grantPermissinOnclick
        activityEcallBinding.grantPermissionBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(ECall.this,
                        Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(ECall.this ,"Permission Granted",Toast.LENGTH_LONG).show();
                }
                else
                {
                        requestCallPermission();
                }
            }
        });


        //callContainer On click
        activityEcallBinding.callCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "tel:"+numkey;
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse(s));
                startActivity(call);
            }
        });





        //onEcallBGClick



            activityEcallBinding.addNumBg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ECallNum = activityEcallBinding.EcallEdit.getText().toString().trim();
                    Name = activityEcallBinding.NameEdit.getText().toString().trim();
                    Relation = activityEcallBinding.RelationEdit.getText().toString().trim();

                    activityEcallBinding.EcallBG.setBackgroundResource(R.drawable.cud_noti);
                    activityEcallBinding.NameBg.setBackgroundResource(R.drawable.cud_noti);
                    activityEcallBinding.RelationBg.setBackgroundResource(R.drawable.cud_noti);
                    activityEcallBinding.grantPermissionBg.setBackgroundResource(R.drawable.cud_noti);


                    if (ECallNum.length() != 10) {
                        //if successful
                        activityEcallBinding.EcallEdit.requestFocus();
                        activityEcallBinding.EcallBG.setBackgroundResource(R.drawable.box_empty);

                    } else if (TextUtils.isEmpty(Name)) {
                        activityEcallBinding.NameBg.requestFocus();
                        activityEcallBinding.NameBg.setBackgroundResource(R.drawable.box_empty);
                    } else if (TextUtils.isEmpty(Relation)) {
                        activityEcallBinding.RelationBg.requestFocus();
                        activityEcallBinding.RelationBg.setBackgroundResource(R.drawable.box_empty);

                    }else if(ContextCompat.checkSelfPermission(ECall.this,
                            Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_DENIED) {
                        activityEcallBinding.grantPermissionBg.setBackgroundResource(R.drawable.box_empty);

                    } else {
                        SharedPreferences.Editor editor = sharedPreferencesECall.edit();
                        editor.putString(KEY_NAME, Name);
                        editor.putString(KEY_NUMBER, activityEcallBinding.EcallEdit.getText().toString());
                        editor.putString(KEY_RELATION, Relation);
                        editor.apply();

                        activityEcallBinding.setNumber.setText(ECallNum);
                        activityEcallBinding.setName.setText(Name);
                        activityEcallBinding.setRelation.setText(Relation);

                        Timer _timer = new Timer();
                        _Animator(activityEcallBinding.ifnotaddesBg, "alpha", 0, 300);


                        TimerTask timer = new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        activityEcallBinding.ifAddedBg.setVisibility(View.VISIBLE);
                                        activityEcallBinding.ifnotaddesBg.setVisibility(View.GONE);
                                        _Animator(activityEcallBinding.ifAddedBg, "alpha", 1, 300);
                                    }
                                });
                            }
                        };
                        _timer.schedule(timer, (int) (200));

                        finish();
                        Toast.makeText(ECall.this, "Emergency Number Added, Please Restart App ." , Toast.LENGTH_SHORT).show();
                    }
                }
            });
//        }else
//        {
//            activityEcallBinding.grantPermissionBg.setBackgroundResource(R.drawable.box_empty);
//        }

        //Background Animation
        LinearLayout bg = findViewById(R.id.bg_);
        AnimationDrawable animationDrawable = (AnimationDrawable) bg.getBackground();
        animationDrawable.setEnterFadeDuration(700);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();


    }

    //Animator Block
    public void _Animator(final View _view, final String _propertyName, final double _value, final double _duration) {
        ObjectAnimator anim = new ObjectAnimator();
        anim.setTarget(_view);
        anim.setPropertyName(_propertyName);
        anim.setFloatValues((float) _value);
        anim.setDuration((long) _duration);
        anim.setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator());
        anim.start();


    }

    //call Request
    private void requestCallPermission()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this ,Manifest.permission.CALL_PHONE))
        {
                new AlertDialog.Builder(this )
                        .setTitle("Permission Needed")
                        .setMessage("Permission needed due to autocall feature !")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(ECall.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
                            }
                        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}