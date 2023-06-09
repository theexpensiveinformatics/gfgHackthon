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
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import error404.gfg.healthcare.databinding.ActivityEcallBinding;


public class ECall extends AppCompatActivity {
    String ECallNum,Name,Relation;
    private static final int REQUEST_OVERLAY_PERMISSION = 0;
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

//        //open keyboard
//        InputMethodManager inputMethodManager =  (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//        inputMethodManager.toggleSoftInputFromWindow(activityEcallBinding.NameEdit.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
//        activityEcallBinding.NameEdit.requestFocus();
//
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            // If not, request the permission
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);

            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
        } else {
            // Permission already granted or not needed
            // Add your overlay window code here

                Timer _timer = new Timer();
                TimerTask timer = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(ECall.this, FloatingViewService.class);
                                i.putExtra("n",numkey);
                                startService(i);
                            }
                        });
                    }
                };
                _timer.schedule(timer, (int)(1100));


//            startService(new Intent(ECall.this,FloatingViewService.class));
        }

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

        activityEcallBinding.grantPermission2Bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(ECall.this)) {
                    // If not, request the permission
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);


//                    activityEcallBinding.grantPermission2Bg.setBackgroundResource(R.drawable.box_empty);

                } else {

                    activityEcallBinding.grantPermission2Bg.setBackgroundResource(R.drawable.cud_noti);
                    Toast.makeText(ECall.this, "Overlay Permission Granted", Toast.LENGTH_SHORT).show();
//                    // Permission already granted or not needed
//                    // Add your overlay window code here
//                    startService(new Intent(ECall.this,FloatingViewService.class));
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
//
//                    }
//
//                    else if(ContextCompat.checkSelfPermission(ECall.this,
//                            Manifest.permission.SYSTEM_ALERT_WINDOW)== PackageManager.PERMISSION_DENIED) {
//                        activityEcallBinding.grantPermission2Bg.setBackgroundResource(R.drawable.box_empty);


                    }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(ECall.this)) {
                        // If not, request the permission
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);


                    }

//                        startService(new Intent(ECall.this,FloatingViewService.class));



                    else {



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


        animationECall();


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


    private void animationECall()
    {
        _Animator(activityEcallBinding.headTxt,"alpha",0,0);
        _Animator(activityEcallBinding.conRound1,"alpha",0,0);
        _Animator(activityEcallBinding.conRound2,"alpha",0,0);
        _Animator(activityEcallBinding.context1,"alpha",0,0);
        _Animator(activityEcallBinding.context2,"alpha",0,0);
        _Animator(activityEcallBinding.cardView1,"alpha",0,0);
        _Animator(activityEcallBinding.cardView2,"alpha",0,0);
        _Animator(activityEcallBinding.des1,"alpha",0,0);
        _Animator(activityEcallBinding.des2,"alpha",0,0);
        _Animator(activityEcallBinding.line1,"alpha",0,0);
        _Animator(activityEcallBinding.line2,"alpha",0,0);
        _Animator(activityEcallBinding.conDetails,"alpha",0,0);

        _Animator(activityEcallBinding.headTxt,"translationX",75,0);
        _Animator(activityEcallBinding.context1,"translationX",-70,0);
        _Animator(activityEcallBinding.context2,"translationX",-70,0);
        _Animator(activityEcallBinding.conRound1,"scaleX",0,0);
        _Animator(activityEcallBinding.conRound2,"scaleX",0,0);
        _Animator(activityEcallBinding.conRound1,"scaleY",0,0);
        _Animator(activityEcallBinding.conRound2,"scaleY",0,0);

        _Animator(activityEcallBinding.cardView1,"translationY",75,0);
        _Animator(activityEcallBinding.cardView2,"translationY",75,0);
        _Animator(activityEcallBinding.des1,"translationY",75,0);
        _Animator(activityEcallBinding.des2,"translationY",75,0);
        _Animator(activityEcallBinding.conDetails,"translationY",75,0);

        _Animator(activityEcallBinding.line1,"translationY",-200,0);
        _Animator(activityEcallBinding.line2,"translationY",-200,0);


        _Animator(activityEcallBinding.headTxt,"translationX",0,250);
        _Animator(activityEcallBinding.headTxt,"alpha",1,250);

        Timer _timer = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.conRound1,"scaleX",1,200);
                        _Animator(activityEcallBinding.conRound1,"scaleY",1,200);
                        _Animator(activityEcallBinding.conRound1,"alpha",1,200);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(200));


        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.context1,"translationX",0,200);
                        _Animator(activityEcallBinding.context1,"alpha",1,200);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(300));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        _Animator(activityEcallBinding.cardView1,"translationY",0,200);
                        _Animator(activityEcallBinding.line1,"translationY",0,400);
                        _Animator(activityEcallBinding.cardView1,"alpha",1,200);
                        _Animator(activityEcallBinding.line1,"alpha",1,400);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(400));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        _Animator(activityEcallBinding.des1,"translationY",0,200);

                        _Animator(activityEcallBinding.des1,"alpha",1,200);

                    }
                });
            }
        };
        _timer.schedule(timer, (int)(500));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.conRound2,"scaleX",1,200);
                        _Animator(activityEcallBinding.conRound2,"scaleY",1,200);
                        _Animator(activityEcallBinding.conRound2,"alpha",1,200);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(600));


        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.context2,"translationX",0,200);
                        _Animator(activityEcallBinding.context2,"alpha",1,200);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(700));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        _Animator(activityEcallBinding.cardView2,"translationY",0,200);
                        _Animator(activityEcallBinding.line2,"translationY",0,400);
                        _Animator(activityEcallBinding.cardView2,"alpha",1,200);
                        _Animator(activityEcallBinding.line2,"alpha",1,400);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(800));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        _Animator(activityEcallBinding.des2,"translationY",0,200);

                        _Animator(activityEcallBinding.des2,"alpha",1,200);

                    }
                });
            }
        };
        _timer.schedule(timer, (int)(900));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        _Animator(activityEcallBinding.conDetails,"translationY",0,200);

                        _Animator(activityEcallBinding.conDetails,"alpha",1,200);

                    }
                });
            }
        };
        _timer.schedule(timer, (int)(1000));

    }


    private void RequestOverlayPermissionPermission()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this ,Manifest.permission.SYSTEM_ALERT_WINDOW))
        {
            new AlertDialog.Builder(this )
                    .setTitle("Permission Needed")
                    .setMessage("Permission needed due to floating Button!")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ECall.this,new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},REQUEST_OVERLAY_PERMISSION);
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
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},REQUEST_OVERLAY_PERMISSION);
        }
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                // Permission granted, add your overlay window code here
            } else {
                // Permission denied, handle accordingly
            }
        }
    }


    @Override
    public void onBackPressed() {

        _Animator(activityEcallBinding.conDetails,"translationY",80,100);
        _Animator(activityEcallBinding.conDetails,"alpha",0,100);

        Timer _timer = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.des2,"translationY",80,100);
                        _Animator(activityEcallBinding.des2,"alpha",0,100);
                        _Animator(activityEcallBinding.line2,"translationY",-200,100);
                        _Animator(activityEcallBinding.line2,"alpha",0,100);

                    }
                });
            }
        };
        _timer.schedule(timer, (int)(50));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.cardView2,"translationY",80,100);
                        _Animator(activityEcallBinding.cardView2,"alpha",0,100);


                    }
                });
            }
        };
        _timer.schedule(timer, (int)(100));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.context2,"translationX",-80,100);
                        _Animator(activityEcallBinding.context2,"alpha",0,100);


                    }
                });
            }
        };
        _timer.schedule(timer, (int)(100));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.conRound2,"scaleX",0,100);
                        _Animator(activityEcallBinding.conRound2,"scaleY",0,100);
                        _Animator(activityEcallBinding.conRound2,"alpha",0,100);


                    }
                });
            }
        };
        _timer.schedule(timer, (int)(150));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.des1,"translationY",80,100);
                        _Animator(activityEcallBinding.des1,"alpha",0,100);
                        _Animator(activityEcallBinding.line1,"translationY",-200,100);
                        _Animator(activityEcallBinding.line1,"alpha",0,100);

                    }
                });
            }
        };
        _timer.schedule(timer, (int)(200));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.cardView1,"translationY",80,100);
                        _Animator(activityEcallBinding.cardView1,"alpha",0,100);


                    }
                });
            }
        };
        _timer.schedule(timer, (int)(250));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.context1,"translationX",-80,100);
                        _Animator(activityEcallBinding.context1,"alpha",0,100);


                    }
                });
            }
        };
        _timer.schedule(timer, (int)(300));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.conRound1,"scaleX",0,100);
                        _Animator(activityEcallBinding.conRound1,"scaleY",0,100);
                        _Animator(activityEcallBinding.conRound1,"alpha",0,100);


                    }
                });
            }
        };
        _timer.schedule(timer, (int)(350));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityEcallBinding.headTxt,"translationX",80,100);
                        _Animator(activityEcallBinding.headTxt,"alpha",0,100);


                    }
                });
            }
        };
        _timer.schedule(timer, (int)(400));
        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ECall.super.onBackPressed();


                    }
                });
            }
        };
        _timer.schedule(timer, (int)(500));




    }
}