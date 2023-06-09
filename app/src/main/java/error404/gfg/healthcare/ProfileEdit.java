package error404.gfg.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

import error404.gfg.healthcare.Token.TokenManager;
import error404.gfg.healthcare.databinding.ActivityProfileEditBinding;
import error404.gfg.healthcare.model.UserModel;
import error404.gfg.healthcare.reotrfit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEdit extends AppCompatActivity {

    RetrofitService retrofitService = new RetrofitService();
    error404.gfg.healthcare.reotrfit.userAPI userAPI = retrofitService.getRetrofit().create(error404.gfg.healthcare.reotrfit.userAPI.class);


    ActivityProfileEditBinding activityProfileEditBinding;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = fAuth.getCurrentUser();
    String uid = firebaseUser.getUid();
    Dialog dialog,loadingDailog;

    private DatabaseReference reference;
    private DatabaseReference dbRef;
    String firstName,lastName,email,bloodGroup,gender,mobileNumber,address,birthDate,confirmPassword, password, key;
    String Str_BloodGroup=null;
    String Str_Gender=null;
    String Str_BirthDate=null;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bg));

        activityProfileEditBinding = ActivityProfileEditBinding.inflate(getLayoutInflater());
        View view = activityProfileEditBinding.getRoot();
        setContentView(view);

        //OnBirthDate Click
        activityProfileEditBinding.BirthDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });

        //when click on A+
        activityProfileEditBinding.ContainerAPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityProfileEditBinding.txtApositive);
                Str_BloodGroup ="A+";
            }
        });

        //when click on A-
        activityProfileEditBinding.ContainerANegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityProfileEditBinding.txtAnegative);
                Str_BloodGroup="A-";
            }
        });

        //when click on AB-
        activityProfileEditBinding.ContainerABNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityProfileEditBinding.txtABnegative);
                Str_BloodGroup="AB-";
            }
        });

        //when click on AB+
        activityProfileEditBinding.ContainerABPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityProfileEditBinding.txtABpositive);
                Str_BloodGroup="AB+";
            }
        });

        //when click on B+
        activityProfileEditBinding.ContainerBPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityProfileEditBinding.txtBpositive);
                Str_BloodGroup="B+";
            }
        });

        //when click on B-
        activityProfileEditBinding.ContainerBNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityProfileEditBinding.txtBnegative);
                Str_BloodGroup="B-";
            }
        });

        //when click on O+
        activityProfileEditBinding.ContainerOPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityProfileEditBinding.txtOpositive);
                Str_BloodGroup="O+";
            }
        });

        //when click on O-
        activityProfileEditBinding.ContainerONegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeBloodGroup(v, activityProfileEditBinding.txtOnegative);
                Str_BloodGroup="O-";
            }
        });

        //when click on Male
        activityProfileEditBinding.ContainerMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGender(v, activityProfileEditBinding.txtMale);
                Str_Gender = "Male";
            }
        });
        //when click on FeMale
        activityProfileEditBinding.ContainerFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGender(v, activityProfileEditBinding.txtFemale);
                Str_Gender = "Female";
            }
        });
        //when click on Other
        activityProfileEditBinding.ContainerOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGender(v, activityProfileEditBinding.txtOther);
                Str_Gender = "Other";
            }
        });
        //when click on Prefernotsay
        activityProfileEditBinding.ContainerPreferNotASay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGender(v, activityProfileEditBinding.txtPrefernotSay);
                Str_Gender = "PreferNotSay";
            }
        });
        
//        This is For FireBase

        DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ProfileData = DBref.child("users").child(uid);

        ProfileData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Retrieve the values from the snapshot and set them on EditText fields
                firstName = snapshot.child("FirstName").getValue(String.class);
                lastName = snapshot.child("LastName").getValue(String.class);
                email = snapshot.child("Email").getValue(String.class);
                mobileNumber = snapshot.child("Number").getValue(String.class);
                gender = snapshot.child("Gender").getValue(String.class);
                birthDate = snapshot.child("BirthDate").getValue(String.class);
                bloodGroup = snapshot.child("BloodGroup").getValue(String.class);
                address = snapshot.child("Address").getValue(String.class);
                key = snapshot.child("key").getValue(String.class);


                // Set the retrieved values on EditText fields
                activityProfileEditBinding.FirstNameEdit.setText(firstName);
                activityProfileEditBinding.LastnameEditText.setText(lastName);
                activityProfileEditBinding.BirthDateText.setText(birthDate);
                activityProfileEditBinding.emailSignupEdit.setText(email);
                activityProfileEditBinding.NumberEdit.setText(mobileNumber);
                activityProfileEditBinding.addressEdit.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
            }
        });

        
        
        
        
//        This is For API
//        TokenManager tokenManager = TokenManager.getInstance(getApplicationContext());
//        String accessToken = tokenManager.getAccessToken();
//
//        Call<UserModel> call = userAPI.getUserProfile("Bearer " + accessToken);
//        call.enqueue(new Callback<UserModel>() {
//            @Override
//            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                if (response.isSuccessful()){
//                    UserModel model = response.body();
//                    Email = model.getEmail();
//                    BirthDate = model.getBirthDate();
//                    BloodGroup = model.getBloodGroup();
//                    Gender = model.getGender();
//                    activityProfileEditBinding.emailSignupEdit.setText(model.getEmail());
//                    activityProfileEditBinding.FirstNameEdit.setText(model.getFirstName());
//                    activityProfileEditBinding.LastnameEditText.setText(model.getLastName());
//                    activityProfileEditBinding.BirthDateText.setText(model.getBirthDate());
//                    activityProfileEditBinding.NumberEdit.setText(model.getNumber());
//                    activityProfileEditBinding.addressEdit.setText(model.getAddress());
//                }else {
//                    Toast.makeText(ProfileEdit.this, response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserModel> call, Throwable t) {
//                Toast.makeText(ProfileEdit.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        activityProfileEditBinding.signUpBtn.setOnClickListener(View -> {
            updateUser();
        });

    }

//    This is for FireBase

    private void updateUser(){
        Str_BirthDate = activityProfileEditBinding.BirthDateText.getText().toString().trim();
        DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ProfileData = DBref.child("users").child(uid);

        HashMap<String, String> user = new HashMap<>();
        user.put("FirstName",activityProfileEditBinding.FirstNameEdit.getText().toString());
        user.put("LastName",activityProfileEditBinding.LastnameEditText.getText().toString());

        user.put("Email",activityProfileEditBinding.emailSignupEdit.getText().toString());
        user.put("Number",activityProfileEditBinding.NumberEdit.getText().toString());
        user.put("Address",activityProfileEditBinding.addressEdit.getText().toString());
        if (Str_BloodGroup == null){
            user.put("BloodGroup", bloodGroup);
        }else {
            user.put("BloodGroup", Str_BloodGroup);
        }
        if(Str_BirthDate == null){
            user.put("BirthDate", birthDate);
        }else {
            user.put("BirthDate", activityProfileEditBinding.BirthDateText.getText().toString());
        }

        if (Str_Gender == null){
            user.put("Gender", gender);
        }else{
            user.put("Gender",Str_Gender);
        }
        user.put("key",key);
        ProfileData.setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"profile updated",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Error !!" + e, Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(getApplicationContext(), home_screen_2.class));
    }




//    This is For API

//    private void updateUser() {
//
//        Str_BirthDate = activityProfileEditBinding.BirthDateText.getText().toString().trim();
//
//        UserModel model = new UserModel();
//
//        model.setFirstName(String.valueOf(activityProfileEditBinding.FirstNameEdit.getText()));
//        model.setLastName(String.valueOf(activityProfileEditBinding.LastnameEditText.getText()));
//        model.setBirthDate(Str_BirthDate);
//        if (Str_BloodGroup == null){
//            model.setBloodGroup(BloodGroup);
//        }else {
//            model.setBloodGroup(Str_BloodGroup);
//        }
//        if (Str_Gender == null){
//            model.setGender(Gender);
//        }else {
//            model.setGender(Str_Gender);
//        }
//        model.setNumber(String.valueOf(activityProfileEditBinding.NumberEdit.getText()));
//        model.setAddress(String.valueOf(activityProfileEditBinding.addressEdit.getText()));
//
//        userAPI.editUser(Email, model)
//                .enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if (response.isSuccessful()){
//                            Toast.makeText(ProfileEdit.this, "Profile Update", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(ProfileEdit.this, response.message(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        Toast.makeText(ProfileEdit.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//        startActivity(new Intent(getApplicationContext(), home_screen_2.class));
//    }

    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                activityProfileEditBinding.BirthDateText.setText(day + "/" + (month + 1) + "/" + year);
                activityProfileEditBinding.BirthDateText.setTextColor(getResources().getColor(R.color.text_primary));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    //bloodGroup
    private void activeBloodGroup(View v, TextView textView)
    {

        activityProfileEditBinding.ContainerAPositive.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerABPositive.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerOPositive.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerBPositive.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerBNegative.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerONegative.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerANegative.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerABNegative.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.txtApositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtBpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtABpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtOpositive.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtOnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtAnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtBnegative.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtABnegative.setTextColor(getResources().getColor(R.color.hintColor));
        textView.setTextColor(Color.WHITE);
        v.setBackgroundResource(R.drawable.blood_bg_active);
    }

    private void activeGender(View v, TextView t)
    {
        activityProfileEditBinding.ContainerMale.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerFemale.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerOther.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.ContainerPreferNotASay.setBackgroundResource(R.drawable.blood_bg_disable);
        activityProfileEditBinding.txtMale.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtFemale.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtOther.setTextColor(getResources().getColor(R.color.hintColor));
        activityProfileEditBinding.txtPrefernotSay.setTextColor(getResources().getColor(R.color.hintColor));
        t.setTextColor(Color.WHITE);
        v.setBackgroundResource(R.drawable.blood_bg_active);
    }

}