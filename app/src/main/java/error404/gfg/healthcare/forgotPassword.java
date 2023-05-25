package error404.gfg.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

import error404.gfg.healthcare.databinding.ActivityFirstAidTipsTwoBinding;
import error404.gfg.healthcare.databinding.ActivityForgotPasswordBinding;

public class forgotPassword extends AppCompatActivity {

    EditText editText1,editText2,editText3,editText4;
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
                activityForgotPasswordBinding.conEnterEmail.setVisibility(View.GONE);
                activityForgotPasswordBinding.enterPassCon.setVisibility(View.GONE);
                activityForgotPasswordBinding.EnterOtpCon.setVisibility(View.VISIBLE);
            }
        });

        activityForgotPasswordBinding.verifyEmailCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityForgotPasswordBinding.conEnterEmail.setVisibility(View.GONE);
                activityForgotPasswordBinding.EnterOtpCon.setVisibility(View.GONE);
                activityForgotPasswordBinding.enterPassCon.setVisibility(View.VISIBLE);
            }
        });


        editText1 = (EditText) findViewById(R.id.otp1);
        editText2 = (EditText) findViewById(R.id.otp2);
        editText3 = (EditText) findViewById(R.id.otp3);
        editText4 = (EditText) findViewById(R.id.otp4);



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
                        if(text.length()==1)
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


        editText1.addTextChangedListener(new GenericTextWatcher(editText1));
        editText2.addTextChangedListener(new GenericTextWatcher(editText2));
        editText3.addTextChangedListener(new GenericTextWatcher(editText3));
        editText4.addTextChangedListener(new GenericTextWatcher(editText4));

    }

    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }




}