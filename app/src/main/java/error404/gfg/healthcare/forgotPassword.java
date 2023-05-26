package error404.gfg.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import error404.gfg.healthcare.databinding.ActivityFirstAidTipsTwoBinding;
import error404.gfg.healthcare.databinding.ActivityForgotPasswordBinding;
import error404.gfg.healthcare.model.UserModel;
import error404.gfg.healthcare.reotrfit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class forgotPassword extends AppCompatActivity {

    RetrofitService retrofitService = new RetrofitService();
    error404.gfg.healthcare.reotrfit.userAPI userAPI = retrofitService.getRetrofit().create(error404.gfg.healthcare.reotrfit.userAPI.class);

    EditText editText1,editText2,editText3,editText4;
    String OTP,PassWord,Email;
ActivityForgotPasswordBinding activityForgotPasswordBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

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
        LinearLayout bg = findViewById(R.id.bgFORGOT);
        AnimationDrawable animationDrawable = (AnimationDrawable) bg.getBackground();
        animationDrawable.setEnterFadeDuration(700);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        //activityBinding
        activityForgotPasswordBinding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        View view = activityForgotPasswordBinding.getRoot();
        setContentView(view);


        activityForgotPasswordBinding.conEnterEmail.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        activityForgotPasswordBinding.conEnterEmail.setVisibility(View.VISIBLE);
        activityForgotPasswordBinding.EnterOtpCon.setVisibility(View.GONE);
        activityForgotPasswordBinding.enterPassCon.setVisibility(View.GONE);

        activityForgotPasswordBinding.conSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel email_send_model = new UserModel();
                Email = activityForgotPasswordBinding.editEmailForgot.getText().toString();
                email_send_model.setEmail(Email);
                userAPI.forgot_password(email_send_model)
                        .enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(forgotPassword.this, "OTP sent to your email", Toast.LENGTH_SHORT).show();
                                    activityForgotPasswordBinding.conEnterEmail.setVisibility(View.GONE);
                                    activityForgotPasswordBinding.enterPassCon.setVisibility(View.GONE);
                                    activityForgotPasswordBinding.EnterOtpCon.setVisibility(View.VISIBLE);
                                } else if (response.code() == 404) {
                                    Toast.makeText(forgotPassword.this, "Email Not found", Toast.LENGTH_SHORT).show();
                                    activityForgotPasswordBinding.conEnterEmail.requestFocus();
                                } else {
                                    Toast.makeText(forgotPassword.this, response.message(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                                Toast.makeText(forgotPassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        editText1 = (EditText) findViewById(R.id.otp1);
        editText2 = (EditText) findViewById(R.id.otp2);
        editText3 = (EditText) findViewById(R.id.otp3);
        editText4 = (EditText) findViewById(R.id.otp4);

        editText1.addTextChangedListener(new GenericTextWatcher(editText1));
        editText2.addTextChangedListener(new GenericTextWatcher(editText2));
        editText3.addTextChangedListener(new GenericTextWatcher(editText3));
        editText4.addTextChangedListener(new GenericTextWatcher(editText4));

        activityForgotPasswordBinding.verifyEmailCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OTP = editText1.getText().toString()+editText2.getText().toString()+editText3.getText().toString()+editText4.getText().toString();

                UserModel verify_otp_model = new UserModel();

                verify_otp_model.setResetToken(OTP);

                userAPI.verify_otp(verify_otp_model)
                        .enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                               if (response.isSuccessful()){
                                   activityForgotPasswordBinding.conEnterEmail.setVisibility(View.GONE);
                                   activityForgotPasswordBinding.EnterOtpCon.setVisibility(View.GONE);
                                   activityForgotPasswordBinding.enterPassCon.setVisibility(View.VISIBLE);
                               } else if (response.code() == 400) {
                                   Toast.makeText(forgotPassword.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                               }else {
                                   Toast.makeText(forgotPassword.this, response.message(), Toast.LENGTH_SHORT).show();
                               }
                            }
                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                                Toast.makeText(forgotPassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        
        activityForgotPasswordBinding.conSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel model = new UserModel();

                model.setEmail(Email);
                model.setNewPassword(activityForgotPasswordBinding.passEdit.getText().toString());

                userAPI.update_password(model)
                        .enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                if (response.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext(), Authantication.class));
                                    Toast.makeText(forgotPassword.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                }else if(response.code() == 500){
                                    Toast.makeText(forgotPassword.this, "Somthig want wrong"+response.message(), Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(forgotPassword.this, response.message(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                                Toast.makeText(forgotPassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        
    }

    class GenericTextWatcher implements TextWatcher
    {
        private View view;
        private GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {

                case R.id.otp1:
                    if(text.length()==0)
                        editText1.requestFocus();
                    else
                        editText2.requestFocus();
                    break;
                case R.id.otp2:
                    if(text.length()==1)
                        editText3.requestFocus();
                    else if(text.length()==0)
                        editText1.requestFocus();
                    break;
                case R.id.otp3:
                    if(text.length()==1)
                        editText4.requestFocus();
                    else if(text.length()==0)
                        editText2.requestFocus();
                    break;
                case R.id.otp4:
                    if(text.length()==0)
                        editText3.requestFocus();
                    else if(text.length() == 1)
                        hideKeybaord(view);
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }




}