package error404.gfg.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import error404.gfg.healthcare.databinding.ActivityAuthSignupBinding;
import error404.gfg.healthcare.databinding.ActivityFirstAidTipsTwoBinding;

public class first_aid_tips_two extends AppCompatActivity {
    private ArrayList<Item> items;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter myAdapter;

    ActivityFirstAidTipsTwoBinding activityFirstAidTipsTwoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid_tips_two);

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
        activityFirstAidTipsTwoBinding = ActivityFirstAidTipsTwoBinding.inflate(getLayoutInflater());
        View view = activityFirstAidTipsTwoBinding.getRoot();
        setContentView(view);


        //search
        myAdapter = new MyAdapter(getApplicationContext(), items);
        activityFirstAidTipsTwoBinding.searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });







        //ArrayList

        items = new ArrayList<Item>();
        items.add(new Item("Asthma Attack",
                "During an asthma attack, the airways in the lungs become narrow, making it difficult to breathe. It is often accompanied by wheezing, coughing, and a feeling of tightness in the chest.",
                R.drawable.asthma,
                "MAJOR",
                "https://www.who.int/news-room/fact-sheets/detail/asthma",
                "https://youtu.be/hdVKpUR513M",
                "Lungs",
                "110011",
                "    Stay calm and try to relax.\n" +
                        "    Use your prescribed inhaler as directed or take quick-relief medication.\n" +
                        "    Sit upright and try to breathe slowly.\n" +
                        "    If symptoms worsen or don't improve after using the inhaler, seek medical help immediately.\n" +
                        "    Follow your asthma action plan and contact your healthcare provider for further guidance.",
                "    Don't panic or exert yourself excessively.\n" +
                        "    Avoid lying flat or bending over, as it can worsen breathing difficulties.\n" +
                        "    Don't ignore or delay treatment if symptoms persist or worsen.\n" +
                        "    Avoid exposure to triggers such as smoke, dust, or allergens.\n" +
                        "    Don't hesitate to call for emergency assistance if symptoms become severe."));

        items.add(new Item("Bleeding",
                "Bleeding is when blood comes out of your body from a cut or injury. It's important to know what to do and what not to do when you or someone else is bleeding. Here's some information in easy language:",
                R.drawable.ic_bleeding,
                "MAJOR",
                "https://www.medindia.net/patients/firstaid_severebleeding.htm",
                "https://youtu.be/NxO5LvgqZe0",
                "Any part of the body",
                "110011",
                "    Stay calm: Try to stay calm and reassure the person who is bleeding. Panicking can make the situation worse.\n" +
                        "    Apply pressure: Use a clean cloth or your hand to apply firm pressure directly on the wound. Press down and hold it there.\n" +
                        "    Elevate the injured area: If possible, raise the bleeding body part above the level of the heart. This can help reduce blood flow to the area.\n" +
                        "    Clean the wound: If the bleeding is not severe, you can gently clean the wound with mild soap and water.\n" +
                        "    Use a bandage or dressing: Once the bleeding has slowed down or stopped, cover the wound with a sterile bandage or dressing to protect it from infection.",
                "        Do not remove any objects: If something is stuck in the wound, like a piece of glass or a knife, do not remove it. Leave it in place and seek medical help.\n" +
                        "    Avoid blowing on the wound: Blowing on the wound might introduce bacteria and increase the risk of infection.\n" +
                        "    Don't use a tourniquet unless absolutely necessary: Tourniquets are tight bands used to stop severe bleeding. They should only be used as a last resort when bleeding cannot be controlled by other means.\n" +
                        "    Do not use dirty or contaminated materials: Make sure to use clean cloth, bandages, or dressings to avoid introducing infection.\n" +
                        "    Don't neglect seeking medical help: If the bleeding is severe, doesn't stop after applying pressure, or if you're unsure about what to do, it's important to seek medical assistance right away."));





        // Initialize the adapter
        myAdapter = new MyAdapter(getApplicationContext(), items);

        // Set the adapter to the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

    }


    private void filter(String text) {
        ArrayList<Item> filteredList = new ArrayList<>();

        for (Item item : items) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        myAdapter.filterList(filteredList);
    }


    private void filterList(String newText)
    {
        List<Item>filteredList = new ArrayList<>();
        for (Item item :items)
        {
            if(item.getTitle().toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        
        if(filteredList.isEmpty())
        {
            Toast.makeText(this, "Not data found ", Toast.LENGTH_SHORT).show();
        }else
        {
            myAdapter.setFilteredList(filteredList);
        }
    }
}