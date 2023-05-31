package error404.gfg.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

import error404.gfg.healthcare.databinding.ActivityAuthSignupBinding;
import error404.gfg.healthcare.model.UserModel;
import error404.gfg.healthcare.reotrfit.RetrofitService;
import error404.gfg.healthcare.reotrfit.userAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Auth_signup extends AppCompatActivity {


    RetrofitService retrofitService = new RetrofitService();
    error404.gfg.healthcare.reotrfit.userAPI userAPI = retrofitService.getRetrofit().create(error404.gfg.healthcare.reotrfit.userAPI.class);


    ActivityAuthSignupBinding activityAuthSignupBinding;
    FirebaseAuth fAuth;
    Dialog dialog,loadingDailog;

    private DatabaseReference reference;
    private DatabaseReference dbRef;
    String FirstName,LastName,Email,Password,ConfirmPassword,MobileNumber,Address;
    String Str_BloodGroup=null;
    String Str_Gender=null;
    String Str_BirthDate=null;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_signup);

        //dailog
        Dailog();

        LoadingDailog();


        //firebase
        fAuth= FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();


        //status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bg));



        //activityBinding
        activityAuthSignupBinding = ActivityAuthSignupBinding.inflate(getLayoutInflater());
        View view = activityAuthSignupBinding.getRoot();
        setContentView(view);


        //OnBirthDate Click
        activityAuthSignupBinding.BirthDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });

        //when click on A+
        activityAuthSignupBinding.ContainerAPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityAuthSignupBinding.txtApositive);
                Str_BloodGroup ="A+";
            }
        });

        //when click on A-
        activityAuthSignupBinding.ContainerANegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v,activityAuthSignupBinding.txtAnegative);
                Str_BloodGroup="A-";
            }
        });

        //when click on AB-
        activityAuthSignupBinding.ContainerABNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v,activityAuthSignupBinding.txtABnegative);
                Str_BloodGroup="AB-";
            }
        });

        //when click on AB+
        activityAuthSignupBinding.ContainerABPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v,activityAuthSignupBinding.txtABpositive);
                Str_BloodGroup="AB+";
            }
        });

        //when click on B+
        activityAuthSignupBinding.ContainerBPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityAuthSignupBinding.txtBpositive);
                Str_BloodGroup="B+";
            }
        });

        //when click on B-
        activityAuthSignupBinding.ContainerBNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v,activityAuthSignupBinding.txtBnegative);
                Str_BloodGroup="B-";
            }
        });

        //when click on O+
        activityAuthSignupBinding.ContainerOPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityAuthSignupBinding.txtOpositive);
                Str_BloodGroup="O+";
            }
        });

        //when click on O-
        activityAuthSignupBinding.ContainerONegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v,activityAuthSignupBinding.txtOnegative);
                Str_BloodGroup="O-";
            }
        });

        //when click on Male
        activityAuthSignupBinding.ContainerMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               activeGender(v,activityAuthSignupBinding.txtMale);
               Str_Gender = "Male";
            }
        });
        //when click on FeMale
        activityAuthSignupBinding.ContainerFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGender(v,activityAuthSignupBinding.txtFemale);
                Str_Gender = "Female";
            }
        });
        //when click on Other
        activityAuthSignupBinding.ContainerOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGender(v,activityAuthSignupBinding.txtOther);
                Str_Gender = "Other";
            }
        });
        //when click on Prefernotsay
        activityAuthSignupBinding.ContainerPreferNotASay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGender(v,activityAuthSignupBinding.txtPrefernotSay);
                Str_Gender = "PreferNotSay";
            }
        });

        // signup on click listener
        activityAuthSignupBinding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validData();
            }
        });

        //topside
        activityAuthSignupBinding.imageTopside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadingDailog.show();
                dialog.show();
            }
        });
    }


    //Method for selecting Date
    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                activityAuthSignupBinding.BirthDateText.setText(day + "/" + (month + 1) + "/" + year);
                activityAuthSignupBinding.BirthDateText.setTextColor(getResources().getColor(R.color.text_primary));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    //bloodGroup
    private void activeBloodGroup(View v,TextView textView)
    {

        activityAuthSignupBinding.ContainerAPositive.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerABPositive.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerOPositive.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerBPositive.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerBNegative.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerONegative.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerANegative.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerABNegative.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.txtApositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtBpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtABpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtOpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtOnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtAnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtBnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtABnegative.setTextColor(getResources().getColor(R.color.hintColor));
        textView.setTextColor(Color.WHITE);
        v.setBackgroundResource(R.drawable.blood_bg_active);
    }

    private void activeGender(View v, TextView t)
    {

        activityAuthSignupBinding.ContainerMale.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerFemale.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerOther.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.ContainerPreferNotASay.setBackgroundResource(R.drawable.blood_bg_disable);
        activityAuthSignupBinding.txtMale.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtFemale.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtOther.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtPrefernotSay.setTextColor(getResources().getColor(R.color.hintColor));
        t.setTextColor(Color.WHITE);
        v.setBackgroundResource(R.drawable.blood_bg_active);
    }

    private void lightColor()
    {
        activityAuthSignupBinding.txtApositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtBpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtABpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtOpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtOnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtAnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtBnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityAuthSignupBinding.txtABnegative.setTextColor(getResources().getColor(R.color.hintColor));
    }


    @SuppressLint("HardwareIds")
    private void validData() {


        FirstName = activityAuthSignupBinding.FirstNameEdit.getText().toString().trim();
        LastName=activityAuthSignupBinding.LastnameEditText.getText().toString().trim();
        Email = activityAuthSignupBinding.emailSignupEdit.getText().toString().trim();
        MobileNumber = activityAuthSignupBinding.NumberEdit.getText().toString().trim();
        Address = activityAuthSignupBinding.addressEdit.getText().toString();
        Password=activityAuthSignupBinding.passwordSignupEdit.getText().toString().trim();
        ConfirmPassword=activityAuthSignupBinding.ConfirmpasswordSignupEdit.getText().toString().trim();
        Str_BirthDate = activityAuthSignupBinding.BirthDateText.getText().toString().trim();
        //BloodGroup
        //Gender
        allDefaultState();

        if (TextUtils.isEmpty(Email))
        {
            activityAuthSignupBinding.signupEmailbg.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.signupEmailbg.requestFocus();
        }
        else if(TextUtils.isEmpty(MobileNumber))
        {
            activityAuthSignupBinding.numberBg.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.numberBg.requestFocus();
        }
        else if(TextUtils.isEmpty(FirstName))
        {
            activityAuthSignupBinding.firstNameBg.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.firstNameBg.requestFocus();
        }
        else if(TextUtils.isEmpty(LastName))
        {
            activityAuthSignupBinding.secondNameBG.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.secondNameBG.requestFocus();
        }
        else if(Str_BirthDate.equals("DD/MM/YYYY"))
        {
            activityAuthSignupBinding.BirthDateBG.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.BirthDateBG.requestFocus();
        }
        else if(TextUtils.isEmpty(Str_BloodGroup))
        {
            activityAuthSignupBinding.BooldGroupBg.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.BooldGroupBg.requestFocus();
        }
        else if(TextUtils.isEmpty(Str_Gender))
        {
            activityAuthSignupBinding.GenderBG.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.GenderBG.requestFocus();
        }
        else if(TextUtils.isEmpty(Address))
        {
            activityAuthSignupBinding.AddressBg.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.AddressBg.requestFocus();
        }

        else if (Password.length()<6)
        {
            activityAuthSignupBinding.signupPasswordBg.requestFocus();
            activityAuthSignupBinding.signupPasswordBg.setBackgroundResource(R.drawable.box_empty);
        }
        else if(!Password.equals(ConfirmPassword))
        {
            activityAuthSignupBinding.signupConfirmPasswordBg.setBackgroundResource(R.drawable.box_empty);
            activityAuthSignupBinding.signupConfirmPasswordBg.requestFocus();
        }

        else {
                createUser();
                loadingDailog.show();
                dialog.show();
        }


    }

    private void allDefaultState()
    {
        activityAuthSignupBinding.signupEmailbg.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.firstNameBg.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.secondNameBG.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.BirthDateBG.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.BooldGroupBg.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.GenderBG.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.signupEmailbg.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.numberBg.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.AddressBg.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.signupPasswordBg.setBackgroundResource(R.drawable.cud_noti);
        activityAuthSignupBinding.signupConfirmPasswordBg.setBackgroundResource(R.drawable.cud_noti);
    }

    private void createUser() {
        //register the users in firebase
        fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Objects.requireNonNull(fAuth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                //write code here if link send successful
//                                Toast.makeText(Auth_signup.this,"Check Your Email Box !",Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(),Authantication.class));
//                                finish();
//                                Toast.makeText(Auth_signup.this,"User Created",Toast.LENGTH_LONG).show();

                                uploadData();
                                loadingDailog.cancel();
                                dialog.show();
                            }
                            else
                            {
                                loadingDailog.cancel();
                                Toast.makeText(Auth_signup.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });

//                    Toast.makeText(Auth_signup.this,"User Created",Toast.LENGTH_LONG).show();
//                    finish();
//                    uploadData();
                }else {
                    loadingDailog.cancel();
                    //write code if user not create account
                    Toast.makeText(Auth_signup.this,"Error !!"+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Auth_signup.this,"Error !!"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void uploadData() {
        String uid = fAuth.getUid();
        dbRef=reference.child("users");
        String key = dbRef.push().getKey();
        reference.child("users");

        HashMap<String, String> user = new HashMap<>();
        user.put("key",key);
        user.put("FirstName",FirstName);
        user.put("LastName",LastName);
        user.put("Email",Email);
        user.put("Number",MobileNumber);
        user.put("Address",Address);
        user.put("BloodGroup",Str_BloodGroup);
        user.put("Gender",Str_Gender);
        user.put("BirthDate", Str_BirthDate);

        dbRef.child(uid).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
//                            Toast.makeText(Auth_signup.this,"user created !!",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(Auth_signup.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Auth_signup.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        FirebaseFirestore.getInstance().collection("users").document(uid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //data updated successfully in FirebaseFirestore
                //Toast.makeText(Auth_signup.this, "yeee lol !", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Auth_signup.this, "Error !!" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    This is for API Create User
//    private void createUser() {
//        //register the users in mongoDB
//        UserModel model = new UserModel();
//
//        model.setFirstName(FirstName);
//        model.setLastName(LastName);
//        model.setBloodGroup(Str_BloodGroup);
//        model.setGender(Str_Gender);
//        model.setEmail(Email);
//        model.setNumber(MobileNumber);
//        model.setAddress(Address);
//        model.setPassword(Password);
//        model.setBirthDate(Str_BirthDate);
//
//        userAPI.AddUser(model)
//                .enqueue(new Callback<UserModel>() {
//                    @Override
//                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                        if (response.code() == 409){
//                            Toast.makeText(Auth_signup.this, response.message() + " : User Email address already exists", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(Auth_signup.this, "User Created", Toast.LENGTH_SHORT).show();
//                            loadingDailog.dismiss();
//                        }
//
//                    }
//                    @Override
//                    public void onFailure(Call<UserModel> call, Throwable t) {
//                        Toast.makeText(Auth_signup.this, t.toString() , Toast.LENGTH_SHORT).show();
//                        loadingDailog.dismiss();
//                    }
//                });
//    }

    public void Dailog()
    {
        //dailog
        dialog = new Dialog(Auth_signup.this);
        dialog.setContentView(R.layout.custom_dailog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dailog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        LinearLayout okay = dialog.findViewById(R.id.btn_okay);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upToIn = new Intent(Auth_signup.this, Authantication.class);
                upToIn.putExtra("Email",Email);
                upToIn.putExtra("Password",Password);
                startActivity(upToIn);
                finish();
            }
        });
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
    }

    public void LoadingDailog()
    {
        //dailogLoading
        loadingDailog = new Dialog(Auth_signup.this);
        loadingDailog.setContentView(R.layout.loading_dailog);
        loadingDailog.getWindow().setBackgroundDrawable(getDrawable(R.color.trans));
        loadingDailog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDailog.setCancelable(false);
        loadingDailog.getWindow().getAttributes().windowAnimations = R.style.animation;
    }

}