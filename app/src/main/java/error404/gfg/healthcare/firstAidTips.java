package error404.gfg.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

//import error404.gfg.healthcare.databinding.ActivityAuthanticationBinding;
import error404.gfg.healthcare.databinding.ActivityFirstAidTipsBinding;

public class firstAidTips extends AppCompatActivity {
    ActivityFirstAidTipsBinding activityFirstAidTipsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid_tips);
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


        //activityBinding
        activityFirstAidTipsBinding = ActivityFirstAidTipsBinding.inflate(getLayoutInflater());
        View view = activityFirstAidTipsBinding.getRoot();
        setContentView(view);


        activityFirstAidTipsBinding.earthQuacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent earthIntent = new Intent(firstAidTips.this,Disasters.class);
                earthIntent.putExtra("type","earthQ");
                startActivity(earthIntent);
            }
        });

        activityFirstAidTipsBinding.fireCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fire = new Intent(firstAidTips.this,Disasters.class);
                fire.putExtra("type","fire");
                startActivity(fire);
            }
        });

        activityFirstAidTipsBinding.earthQuacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent earthIntent = new Intent(firstAidTips.this,Disasters.class);
                earthIntent.putExtra("type","Flood");
                startActivity(earthIntent);
            }
        });

        activityFirstAidTipsBinding.earthQuacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent earthIntent = new Intent(firstAidTips.this,Disasters.class);
                earthIntent.putExtra("type","Tsunami");
                startActivity(earthIntent);
            }
        });


        activityFirstAidTipsBinding.earthQuacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent earthIntent = new Intent(firstAidTips.this,Disasters.class);
                earthIntent.putExtra("type","Land Slides");
                startActivity(earthIntent);
            }
        });

        activityFirstAidTipsBinding.earthQuacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent earthIntent = new Intent(firstAidTips.this,Disasters.class);
                earthIntent.putExtra("type","Cyclones");
                startActivity(earthIntent);
            }
        });


    }
}