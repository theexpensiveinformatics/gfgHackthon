package error404.gfg.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import error404.gfg.healthcare.databinding.ActivityDisastersBinding;

public class Disasters extends AppCompatActivity {

    ActivityDisastersBinding activityDisastersBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disasters);

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
        activityDisastersBinding = ActivityDisastersBinding.inflate(getLayoutInflater());
        View view = activityDisastersBinding.getRoot();
        setContentView(view);

        //getType
        String type = getIntent().getExtras().getString("type");

        if(type.equals("earthQ"))
        {
            //imageView
            activityDisastersBinding.imageDis.setImageResource(R.drawable.ic_earthquackes);

            //containers
            activityDisastersBinding.conOne.setVisibility(View.VISIBLE);
            activityDisastersBinding.conTwo.setVisibility(View.VISIBLE);
            activityDisastersBinding.conThree.setVisibility(View.VISIBLE);
            activityDisastersBinding.conFour.setVisibility(View.GONE);

            //text & Strings
            activityDisastersBinding.headtxt.setText(R.string.earth_quakes);
            activityDisastersBinding.oneHeadTxt.setText(R.string.before);
            activityDisastersBinding.oneDesTxt.setText(R.string.earth_before);
            activityDisastersBinding.twoHeadTxt.setText(R.string.during);
            activityDisastersBinding.twoDesTxt.setText(R.string.earth_during);
            activityDisastersBinding.threeHeadTxt.setText(R.string.after);
            activityDisastersBinding.threeDesTxt.setText(R.string.earth_after);

        }

        if(type.equals("fire"))
        {
            //imageView
            activityDisastersBinding.imageDis.setImageResource(R.drawable.ic_fire);

            //containers
            activityDisastersBinding.conOne.setVisibility(View.VISIBLE);
            activityDisastersBinding.conTwo.setVisibility(View.VISIBLE);
            activityDisastersBinding.conThree.setVisibility(View.VISIBLE);
            activityDisastersBinding.conFour.setVisibility(View.VISIBLE);

            //text & Strings
            activityDisastersBinding.headtxt.setText(R.string.fire);
            activityDisastersBinding.oneHeadTxt.setText(R.string.fire_head1);
            activityDisastersBinding.oneDesTxt.setText(R.string.fire_des1);
            activityDisastersBinding.twoHeadTxt.setText(R.string.fire_head2);
            activityDisastersBinding.threeHeadTxt.setText(R.string.fire_head3);
            activityDisastersBinding.fourHeadTxt.setText(R.string.fire_head4);

        }


    }
}