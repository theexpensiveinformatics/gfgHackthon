package error404.gfg.healthcare;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.LineHeightSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import androidx.core.content.res.ResourcesCompat;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class FirstAidArticle extends AppCompatActivity {
    TextView titleTxt,descriptionTxt,typeTxt,effectedPartTxt,doTxt,dontxt;
    ImageView img,imageViewHead;
    LinearLayout back,sound1,sound2,sound3,web,yt,sound;
    TextToSpeech t1;
    private Handler handler;

    TextToSpeech tts;
    CardView cardView;
    LinearLayout one,two,three;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid_article);



//        //animation Background
//        LinearLayout bg = findViewById(R.id.bg);
//        AnimationDrawable animationDrawable = (AnimationDrawable) bg.getBackground();
//        animationDrawable.setEnterFadeDuration(700);
//        animationDrawable.setExitFadeDuration(3000);
//        animationDrawable.start();
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


        titleTxt=(TextView)     findViewById(R.id.titleTxt);
        descriptionTxt=(TextView)     findViewById(R.id.descriptionTxt);
        typeTxt=(TextView)     findViewById(R.id.typeTxt);
        effectedPartTxt=(TextView)     findViewById(R.id.effectedPartTxt);
        doTxt=(TextView)     findViewById(R.id.doTxt);
        dontxt=(TextView)     findViewById(R.id.dontTxt);
        img=(ImageView) findViewById(R.id.imageView8);
        web=(LinearLayout) findViewById(R.id.webBtn);
        cardView=(CardView)findViewById(R.id.cardView);
        one=(LinearLayout)findViewById(R.id.one);
        two=(LinearLayout)findViewById(R.id.two);
        three=(LinearLayout)findViewById(R.id.three);
        yt=(LinearLayout) findViewById(R.id.ytBtn);
        sound=(LinearLayout) findViewById(R.id.soundBtn);
        sound1=(LinearLayout)findViewById(R.id.soundBtndes) ;
        sound2=(LinearLayout)findViewById(R.id.soundBtndo) ;
        sound3=(LinearLayout)findViewById(R.id.soundBtndont);
        back=(LinearLayout)findViewById(R.id.backBtn);

        String title = getIntent().getExtras().getString("title");
        String description = getIntent().getExtras().getString("description");
        String type = getIntent().getExtras().getString("type");
        String effectedPart = getIntent().getExtras().getString("effectedPart");
        String doS = getIntent().getExtras().getString("do");
        String dontS = getIntent().getExtras().getString("dont");
        String weblink = getIntent().getExtras().getString("wLink");
        String ytlink = getIntent().getExtras().getString("yLink");
        String voice = title+". " +description+" " +"What to do ?"+" " +doS+" "+"what not to do?"+dontS;


        titleTxt.setText(title);
        descriptionTxt.setText(description);
        typeTxt.setText(type);
        effectedPartTxt.setText(effectedPart);
        int imageResourceId = getIntent().getIntExtra("imageResourceId", 0);
        img.setImageResource(imageResourceId);


        String[] doSentences = doS.split("\\    ");
        String[] dontSentences = dontS.split("\\    ");

// Create a SpannableStringBuilder to build the formatted strings
        SpannableStringBuilder formattedDoS = new SpannableStringBuilder();
        SpannableStringBuilder formattedDontS = new SpannableStringBuilder();

// Create a BulletSpan for the bullet symbol
        BulletSpan bulletSpan = new BulletSpan(20);

// Iterate over each sentence in the "do" string
        for (String sentence : doSentences) {

            formattedDoS.append(sentence);
            formattedDoS.append(" • ");
            formattedDoS.setSpan(bulletSpan, formattedDoS.length() - sentence.length() - 1, formattedDoS.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            formattedDoS.setSpan(new LineHeightSpan() {
                @Override
                public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm) {
                    fm.bottom += 20; // Adjust the line height as needed
                }
            }, formattedDoS.length() - sentence.length() - 1, formattedDoS.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

// Iterate over each sentence in the "don't" string
        for (String sentence : dontSentences) {

            formattedDontS.append(sentence);
            formattedDontS.append(" • ");
            formattedDontS.setSpan(bulletSpan, formattedDontS.length() - sentence.length() - 1, formattedDontS.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            formattedDontS.setSpan(new LineHeightSpan() {
                @Override
                public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm) {
                    fm.bottom += 20; // Adjust the line height as needed
                }
            }, formattedDontS.length() - sentence.length() - 1, formattedDontS.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

// Set the formatted strings in the TextViews
        doTxt.setText(formattedDoS);
        dontxt.setText(formattedDontS);


        //text to speech two



        handler = new Handler();


        //text to speech
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });


        sound3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the 'doTxt' TextView
                String doText = dontxt.getText().toString();

                // Split the text into individual sentences
                final String[] sentences = doText.split("•");

                // Create a SpannableStringBuilder to build the highlighted text
                final SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(doText);

                // Create a BackgroundColorSpan to set the background color
                final BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(getResources().getColor(R.color.light_blue));

                // Start highlighting and speaking the sentences
                highlightAndSpeakSentence3(0, sentences, spannableBuilder, backgroundColorSpan);
            }
        });

        sound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the 'doTxt' TextView
                String doText = doTxt.getText().toString();

                // Split the text into individual sentences
                final String[] sentences = doText.split("•");

                // Create a SpannableStringBuilder to build the highlighted text
                final SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(doText);

                // Create a BackgroundColorSpan to set the background color
                final BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(getResources().getColor(R.color.light_blue));

                // Start highlighting and speaking the sentences
                highlightAndSpeakSentence(0, sentences, spannableBuilder, backgroundColorSpan);
            }
        });

        sound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the 'doTxt' TextView
                String desS = descriptionTxt.getText().toString();

                // Split the text into individual sentences
                final String[] sentences = desS.split("\\.");

                // Create a SpannableStringBuilder to build the highlighted text
                final SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(desS);

                // Create a BackgroundColorSpan to set the background color
                final BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(getResources().getColor(R.color.light_blue));

                // Start highlighting and speaking the sentences
                highlightAndSpeakSentence2(0, sentences, spannableBuilder, backgroundColorSpan);
            }
        });






















        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //web and yt

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(weblink));
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent yt = new Intent(Intent.ACTION_VIEW);
                    yt.setData(Uri.parse(ytlink));
                    startActivity(yt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        animationArticle();

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

    private void animationArticle(){
        _Animator(back,"translationX",75,0);
        _Animator(sound,"translationX",75,0);
        _Animator(web,"translationX",75,0);
        _Animator(yt,"translationX",75,0);

        _Animator(back,"alpha",0,0);
        _Animator(cardView,"alpha",0,0);
        _Animator(cardView,"translationY",80,0);
        _Animator(sound,"alpha",0,0);
        _Animator(web,"alpha",0,0);
        _Animator(yt,"alpha",0,0);
        _Animator(back,"translationX",0,250);
        _Animator(back,"alpha",1,250);


        _Animator(one,"alpha",0,0);
        _Animator(two,"alpha",0,0);
        _Animator(three,"alpha",0,0);
        _Animator(one,"translationY",80,0);
        _Animator(two,"translationY",80,0);
        _Animator(three,"translationY",80,0);

        Timer _timer = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(sound,"translationX",0,250);
                        _Animator(sound,"alpha",1,250);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(150));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(web,"translationX",0,250);
                        _Animator(web,"alpha",1,250);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(300));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(yt,"translationX",0,250);
                        _Animator(yt,"alpha",1,250);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(450));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(cardView,"translationY",0,300);
                        _Animator(cardView,"alpha",1,300);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(400));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(one,"translationY",0,300);
                        _Animator(one,"alpha",1,300);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(550));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(two,"translationY",0,300);
                        _Animator(two,"alpha",1,300);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(700));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(three,"translationY",0,300);
                        _Animator(three,"alpha",1,300);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(850));



    }

    @Override
    public void onBackPressed() {

        _Animator(yt,"transaltionY",75,130);
        _Animator(yt,"alpha",0,130);

        Timer _timer = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(web,"transaltionY",75,130);
                        _Animator(web,"alpha",0,130);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(65));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(sound,"transaltionY",75,130);
                        _Animator(sound,"alpha",0,130);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(130));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(back,"transaltionY",75,130);
                        _Animator(back,"alpha",0,130);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(195));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(three,"translationY",80,180);
                        _Animator(three,"alpha",0,180);
                        _Animator(two,"translationY",80,180);
                        _Animator(two,"alpha",0,180);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(250));


        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(one,"translationY",80,180);
                        _Animator(one,"alpha",0,180);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(315));


        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(cardView,"translationY",80,180);
                        _Animator(cardView,"alpha",0,180);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(380));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FirstAidArticle.super.onBackPressed();
                        t1.stop();
                        finish();
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(520));






    }


    private void highlightAndSpeakSentence(final int sentenceIndex, final String[] sentences, final SpannableStringBuilder spannableBuilder, final BackgroundColorSpan backgroundColorSpan) {
        if (sentenceIndex >= sentences.length) {
            // All sentences have been processed
            return;
        }

        final String sentence = sentences[sentenceIndex].trim();

        // Get the start and end indices of the current sentence
        int start = spannableBuilder.toString().indexOf(sentence);
        int end = start + sentence.length();

        // Apply the BackgroundColorSpan to the current sentence
        spannableBuilder.setSpan(backgroundColorSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        // Set the highlighted text in the 'doTxt' TextView
        doTxt.setText(spannableBuilder);

        // Speak the current sentence
        t1.speak(sentence, TextToSpeech.QUEUE_FLUSH, null);

        // Delay the highlighting and speaking by the duration of the current sentence
        int delay = sentence.length() * 100; // Adjust the multiplier as needed

        // Schedule the removal of highlighting and proceed to the next sentence
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Remove the highlighting from the current sentence
                spannableBuilder.removeSpan(backgroundColorSpan);
                doTxt.setText(spannableBuilder);

                // Proceed to the next sentence
                highlightAndSpeakSentence(sentenceIndex + 1, sentences, spannableBuilder, backgroundColorSpan);
            }
        }, delay);
    }
    private void highlightAndSpeakSentence2(final int sentenceIndex, final String[] sentences, final SpannableStringBuilder spannableBuilder, final BackgroundColorSpan backgroundColorSpan) {
        if (sentenceIndex >= sentences.length) {
            // All sentences have been processed
            return;
        }

        final String sentence = sentences[sentenceIndex].trim();

        // Get the start and end indices of the current sentence
        int start = spannableBuilder.toString().indexOf(sentence);
        int end = start + sentence.length();

        // Apply the BackgroundColorSpan to the current sentence
        spannableBuilder.setSpan(backgroundColorSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        // Set the highlighted text in the 'doTxt' TextView
        descriptionTxt.setText(spannableBuilder);

        // Speak the current sentence
        t1.speak(sentence, TextToSpeech.QUEUE_FLUSH, null);

        // Delay the highlighting and speaking by the duration of the current sentence
        int delay = sentence.length() * 90; // Adjust the multiplier as needed

        // Schedule the removal of highlighting and proceed to the next sentence
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Remove the highlighting from the current sentence
                spannableBuilder.removeSpan(backgroundColorSpan);
                descriptionTxt.setText(spannableBuilder);

                // Proceed to the next sentence
                highlightAndSpeakSentence2(sentenceIndex + 1, sentences, spannableBuilder, backgroundColorSpan);
            }
        }, delay);
    }

    private void highlightAndSpeakSentence3(final int sentenceIndex, final String[] sentences, final SpannableStringBuilder spannableBuilder, final BackgroundColorSpan backgroundColorSpan) {
        if (sentenceIndex >= sentences.length) {
            // All sentences have been processed
            return;
        }

        final String sentence = sentences[sentenceIndex].trim();

        // Get the start and end indices of the current sentence
        int start = spannableBuilder.toString().indexOf(sentence);
        int end = start + sentence.length();

        // Apply the BackgroundColorSpan to the current sentence
        spannableBuilder.setSpan(backgroundColorSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        // Set the highlighted text in the 'doTxt' TextView
        dontxt.setText(spannableBuilder);

        // Speak the current sentence
        t1.speak(sentence, TextToSpeech.QUEUE_FLUSH, null);

        // Delay the highlighting and speaking by the duration of the current sentence
        int delay = sentence.length() * 90; // Adjust the multiplier as needed

        // Schedule the removal of highlighting and proceed to the next sentence
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Remove the highlighting from the current sentence
                spannableBuilder.removeSpan(backgroundColorSpan);
                dontxt.setText(spannableBuilder);

                // Proceed to the next sentence
                highlightAndSpeakSentence3(sentenceIndex + 1, sentences, spannableBuilder, backgroundColorSpan);
            }
        }, delay);
    }



}