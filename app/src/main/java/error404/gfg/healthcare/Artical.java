package error404.gfg.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import  error404.gfg.healthcare.databinding.ActivityArticalBinding;

public class Artical extends AppCompatActivity {
   ActivityArticalBinding activityArticalBinding;
    TextView headline , description , publisher , time;
    LinearLayout weblink , ytlink ;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artical);
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

        activityArticalBinding =ActivityArticalBinding.inflate(getLayoutInflater());
        View view = activityArticalBinding.getRoot();
        setContentView(view);

       //animation Background
        //animation Background
        LinearLayout constraintLayout = findViewById(R.id.arcbg);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(700);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        image=findViewById(R.id.imageArc);
        headline= findViewById(R.id.headtxt);
        description=findViewById(R.id.description_arc);
        publisher =findViewById(R.id.publisherArc);
        time=findViewById(R.id.timeArc);
        weblink=findViewById(R.id.weblinkBg);
        ytlink=findViewById(R.id.ytLinkBg);

        String sheadline = getIntent().getExtras().getString("headline");
        String sdescription = getIntent().getExtras().getString("description");
        String spublisher = getIntent().getExtras().getString("publisher");
        String stime = getIntent().getExtras().getString("time");
        String sweblink = getIntent().getExtras().getString("weblink");
        String sytlink = getIntent().getExtras().getString("ytlink");


        headline.setText(sheadline);
        description.setText(sdescription);
        publisher.setText(spublisher);
        time.setText(stime);
        Picasso.get().load(getIntent().getStringExtra("image")).placeholder(R.drawable.medical).into(image);

        weblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                {
                    try {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(sweblink));
                        startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Handle the exception when no activity is found to handle the intent
                        Toast.makeText(getApplicationContext(), "No application found to open the link.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        // Handle any other unexpected exceptions
                        Toast.makeText(getApplicationContext(), "No application found to open the link.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ytlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sytlink.equals("noLink")) {
                    Toast.makeText(Artical.this, "No Youtube link attached.", Toast.LENGTH_SHORT).show();
                }else
                {
                    try {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(sytlink));
                        startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Handle the exception when no activity is found to handle the intent
                        Toast.makeText(getApplicationContext(), "No application found to open the link.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        // Handle any other unexpected exceptions
                        Toast.makeText(getApplicationContext(), "No application found to open the link.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}