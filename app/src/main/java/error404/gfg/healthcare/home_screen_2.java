package error404.gfg.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


import error404.gfg.healthcare.databinding.ActivityHomeScreen2Binding;

public class home_screen_2 extends AppCompatActivity {


    ActivityHomeScreen2Binding activityHomeScreen2Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen2);


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

        //activity binding
        //activityBinding
        activityHomeScreen2Binding = ActivityHomeScreen2Binding.inflate(getLayoutInflater());
        View view = activityHomeScreen2Binding.getRoot();
        setContentView(view);


        LinearLayout constraintLayout = findViewById(R.id.bg);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(700);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        //for starting bottom setting
        activityHomeScreen2Binding.firstCon.setScaleX(1f);
        activityHomeScreen2Binding.firstCon.setScaleY(1f);
        activityHomeScreen2Binding.secondCon.setScaleX(0.8f);
        activityHomeScreen2Binding.secondCon.setScaleY(0.8f);
        activityHomeScreen2Binding.thirdCon.setScaleX(0.8f);
        activityHomeScreen2Binding.thirdCon.setScaleY(0.8f);
        activityHomeScreen2Binding.fourCon.setScaleX(0.8f);
        activityHomeScreen2Binding.fourCon.setScaleY(0.8f);

        activityHomeScreen2Binding.secondCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new HistoryFragment());

                activityHomeScreen2Binding.firstImg.setImageResource(R.drawable.ic_home_outline);
                activityHomeScreen2Binding.secondImg.setImageResource(R.drawable.ic_s_history_fill);
                activityHomeScreen2Binding.thirdImg.setImageResource(R.drawable.ic_explore_outline);
                activityHomeScreen2Binding.fourImg.setImageResource(R.drawable.ic_profile_outline);

                _Animator(activityHomeScreen2Binding.secondCon,"scaleX",1,200);
                _Animator(activityHomeScreen2Binding.secondCon,"scaleY",1,200);
                _Animator(activityHomeScreen2Binding.firstCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.firstCon,"scaleY",0.8,200);
                _Animator(activityHomeScreen2Binding.thirdCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.thirdCon,"scaleY",0.8,200);
                _Animator(activityHomeScreen2Binding.fourCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.fourCon,"scaleY",0.8,200);
            }
        });


        activityHomeScreen2Binding.thirdCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new ThirdFragment());
                activityHomeScreen2Binding.firstImg.setImageResource(R.drawable.ic_home_outline);
                activityHomeScreen2Binding.secondImg.setImageResource(R.drawable.ic_s_history_outline);
                activityHomeScreen2Binding.thirdImg.setImageResource(R.drawable.ic_explore_fill);
                activityHomeScreen2Binding.fourImg.setImageResource(R.drawable.ic_profile_outline);

                _Animator(activityHomeScreen2Binding.thirdCon,"scaleX",1,200);
                _Animator(activityHomeScreen2Binding.thirdCon,"scaleY",1,200);
                _Animator(activityHomeScreen2Binding.firstCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.firstCon,"scaleY",0.8,200);
                _Animator(activityHomeScreen2Binding.secondCon,"scaleY",0.8,200);
                _Animator(activityHomeScreen2Binding.secondCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.fourCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.fourCon,"scaleY",0.8,200);
            }
        });

        activityHomeScreen2Binding.fourCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ProfileFragment());
                activityHomeScreen2Binding.firstImg.setImageResource(R.drawable.ic_home_outline);
                activityHomeScreen2Binding.secondImg.setImageResource(R.drawable.ic_s_history_outline);
                activityHomeScreen2Binding.thirdImg.setImageResource(R.drawable.ic_explore_outline);
                activityHomeScreen2Binding.fourImg.setImageResource(R.drawable.ic_profile_fill);

                _Animator(activityHomeScreen2Binding.fourCon,"scaleX",1,200);
                _Animator(activityHomeScreen2Binding.fourCon,"scaleY",1,200);
                _Animator(activityHomeScreen2Binding.firstCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.firstCon,"scaleY",0.8,200);
                _Animator(activityHomeScreen2Binding.thirdCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.thirdCon,"scaleY",0.8,200);
                _Animator(activityHomeScreen2Binding.secondCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.secondCon,"scaleY",0.8,200);
            }
        });
        activityHomeScreen2Binding.firstCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeFragment());
                activityHomeScreen2Binding.firstImg.setImageResource(R.drawable.ic_home_fill);
                activityHomeScreen2Binding.secondImg.setImageResource(R.drawable.ic_s_history_outline);
                activityHomeScreen2Binding.thirdImg.setImageResource(R.drawable.ic_explore_outline);
                activityHomeScreen2Binding.fourImg.setImageResource(R.drawable.ic_profile_outline);

                _Animator(activityHomeScreen2Binding.firstCon,"scaleX",1,200);
                _Animator(activityHomeScreen2Binding.firstCon,"scaleY",1,200);
                _Animator(activityHomeScreen2Binding.secondCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.secondCon,"scaleY",0.8,200);
                _Animator(activityHomeScreen2Binding.thirdCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.thirdCon,"scaleY",0.8,200);
                _Animator(activityHomeScreen2Binding.fourCon,"scaleX",0.8,200);
                _Animator(activityHomeScreen2Binding.fourCon,"scaleY",0.8,200);
            }
        });



        //framelayout
        replaceFragment(new HomeFragment());

    }
    //Animator Block
    public void _Animator (final View _view, final String _propertyName, final double _value, final double _duration) {
        ObjectAnimator anim = new ObjectAnimator();
        anim.setTarget(_view);
        anim.setPropertyName(_propertyName);
        anim.setFloatValues((float)_value);
        anim.setDuration((long)_duration);
        anim.setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator());
        anim.start();
    }


    private  void replaceFragment(Fragment fragment)
    {
//        FragmentManager fragmentManager  = getSupportFragmentManager();
//        fragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_anim,R.anim.fragment_anim_2);
//        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, fragment);
//        fragmentTransaction.commit();

        FragmentManager fragmentManager  = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_anim_2,R.anim.fragment_anim)
                .replace(R.id.frameLayout, fragment);
                fragmentTransaction.commit();
    }
}