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

        if(type.equals("Flood"))
        {
            //imageView
            activityDisastersBinding.imageDis.setImageResource(R.drawable.ic_flood);

            //containers
            activityDisastersBinding.conOne.setVisibility(View.VISIBLE);
            activityDisastersBinding.conTwo.setVisibility(View.VISIBLE);
            activityDisastersBinding.conThree.setVisibility(View.VISIBLE);
            activityDisastersBinding.conFour.setVisibility(View.VISIBLE);

            //text & Strings
            activityDisastersBinding.headtxt.setText("Flood");
            activityDisastersBinding.oneHeadTxt.setText(R.string.flood_head1);
            activityDisastersBinding.oneDesTxt.setText(R.string.flood_text1);
            activityDisastersBinding.twoHeadTxt.setText(R.string.flood_head2);
            activityDisastersBinding.twoDesTxt.setText(R.string.flood_text2);
            activityDisastersBinding.threeHeadTxt.setText(R.string.flood_head3);
            activityDisastersBinding.threeDesTxt.setText(R.string.flood_text3);
            activityDisastersBinding.fourHeadTxt.setText(R.string.flood_head4);
            activityDisastersBinding.fourDesTxt.setText(R.string.flood_text4);

        }

        if(type.equals("Tsunami"))
        {
            //imageView
            activityDisastersBinding.imageDis.setImageResource(R.drawable.ic_tsunami);

            //containers
            activityDisastersBinding.conOne.setVisibility(View.VISIBLE);
            activityDisastersBinding.conTwo.setVisibility(View.VISIBLE);
            activityDisastersBinding.conThree.setVisibility(View.VISIBLE);
            activityDisastersBinding.conFour.setVisibility(View.GONE);

            //text & Strings
            activityDisastersBinding.headtxt.setText("Tsunami");
            activityDisastersBinding.oneHeadTxt.setText(R.string.tsunami_head1);
            activityDisastersBinding.oneDesTxt.setText(R.string.tsunami_text1);
            activityDisastersBinding.twoHeadTxt.setText(R.string.tsunami_head2);
            activityDisastersBinding.twoDesTxt.setText(R.string.tsunami_text2);
            activityDisastersBinding.threeHeadTxt.setText(R.string.tsunami_head3);
            activityDisastersBinding.threeDesTxt.setText(R.string.tsunami_text3);

        }


        if(type.equals("Land Slides"))
        {
            //imageView
            activityDisastersBinding.imageDis.setImageResource(R.drawable.ic_landslide);

            //containers
            activityDisastersBinding.conOne.setVisibility(View.VISIBLE);
            activityDisastersBinding.conTwo.setVisibility(View.VISIBLE);
            activityDisastersBinding.conThree.setVisibility(View.VISIBLE);
            activityDisastersBinding.conFour.setVisibility(View.GONE);

            //text & Strings
            activityDisastersBinding.headtxt.setText("Land Slides");
            activityDisastersBinding.oneHeadTxt.setText(R.string.landslide_head1);
            activityDisastersBinding.oneDesTxt.setText(R.string.landslide_text1);
            activityDisastersBinding.twoHeadTxt.setText(R.string.landslide_head2);
            activityDisastersBinding.twoDesTxt.setText(R.string.landslide_text2);
            activityDisastersBinding.threeHeadTxt.setText(R.string.landslide_head3);
            activityDisastersBinding.threeDesTxt.setText(R.string.landslide_text3);

        }


        if(type.equals("Cyclones"))
        {
            //imageView
            activityDisastersBinding.imageDis.setImageResource(R.drawable.ic_cyclones);

            //containers
            activityDisastersBinding.conOne.setVisibility(View.VISIBLE);
            activityDisastersBinding.conTwo.setVisibility(View.VISIBLE);
            activityDisastersBinding.conThree.setVisibility(View.VISIBLE);
            activityDisastersBinding.conFour.setVisibility(View.VISIBLE);

            //text & Strings
            activityDisastersBinding.headtxt.setText("Cyclones");
            activityDisastersBinding.oneHeadTxt.setText(R.string.cyclones_head1);
            activityDisastersBinding.oneDesTxt.setText(R.string.cyclones_text1);
            activityDisastersBinding.twoHeadTxt.setText(R.string.cyclones_head2);
            activityDisastersBinding.twoDesTxt.setText(R.string.cyclones_text2);
            activityDisastersBinding.threeHeadTxt.setText(R.string.cyclones_head3);
            activityDisastersBinding.threeDesTxt.setText(R.string.cyclones_text3);
            activityDisastersBinding.fourHeadTxt.setText(R.string.cyclones_head4);
            activityDisastersBinding.fourDesTxt.setText(R.string.cyclones_text4);

        }


    }
}