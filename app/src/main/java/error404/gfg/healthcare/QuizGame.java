package error404.gfg.healthcare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import error404.gfg.healthcare.databinding.ActivityQuizGameBinding;


public class QuizGame extends AppCompatActivity {


    ActivityQuizGameBinding binding;
    FirebaseFirestore database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityQuizGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        binding = ActivityQuizGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        database = FirebaseFirestore.getInstance();

        final ArrayList<CategoryModel> categories = new ArrayList<>();

        final CategoryAdapter adapter = new CategoryAdapter(QuizGame.this, categories);



        database.collection("categories")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {

                            return;
                        }
                        categories.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            CategoryModel model = snapshot.toObject(CategoryModel.class);
                            model.setCategoryId(snapshot.getId());
                            categories.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });



        binding.categoryList.setLayoutManager(new GridLayoutManager(QuizGame.this,1));
        binding.categoryList.setAdapter(adapter);


        binding.textView15.setTranslationY(70);
        binding.textView6.setTranslationY(70);
        binding.textView67.setTranslationY(70);
        binding.categoryList.setTranslationY(70);
        binding.textView15.setAlpha(0);
        binding.textView6.setAlpha(0);
        binding.textView67.setAlpha(0);
        binding.categoryList.setAlpha(0);


        _Animator(binding.textView15,"alpha",1,200);
        _Animator(binding.textView15,"translationY",0,200);


        Timer _timer = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(binding.textView6,"translationY",0,200);
                        _Animator(binding.textView6,"alpha",1,200);
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
                        _Animator(binding.textView67,"translationY",0,200);
                        _Animator(binding.textView67,"alpha",1,200);
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
                        _Animator(binding.categoryList,"translationY",0,200);
                        _Animator(binding.categoryList,"alpha",1,200);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(200));



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

}