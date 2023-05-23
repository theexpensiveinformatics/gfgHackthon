package error404.gfg.healthcare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
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
import java.util.Timer;
import java.util.TimerTask;

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

        //ArrayList

        items = new ArrayList<Item>();
        items.add(new Item("Asthma Attack",
                "During an asthma attack, the airways in the lungs become narrow, making it difficult to breathe. It is often accompanied by wheezing, coughing, and a feeling of tightness in the chest.",
                R.drawable.asthma,
                "MAJOR",
                "https://www.wikihow.com/Treat-Asthma-Attacks",
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
                "https://www.wikihow.com/Stop-Bleeding",
                "https://youtu.be/8pTaqY40-Rs",
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

        items.add(new Item("Bones & Muscles",
                "Bones and muscles injuries refer to damage or harm to the skeletal system or the muscular system of the body. These injuries can occur due to accidents, falls, sports activities, or repetitive strain.",
                R.drawable.ic_bones,
                "MAJOR",
                "https://www.wikihow.com/Heal-Broken-Bones",
                "https://youtu.be/2v8vlXgGXwE",
                "Any part of body",
                "110011",
                "    Rest the injured area and avoid putting weight or strain on it.\n" +
                        "    Apply ice packs or cold compresses to reduce swelling and pain.\n" +
                        "    Compress the area gently with a bandage or wrap to provide support.\n" +
                        "    Elevate the injured limb or body part to reduce swelling.\n" +
                        "    Seek medical attention for severe pain, inability to move, or visible deformity.",
                "    Don't try to move or force the injured area if it causes severe pain.\n" +
                        "    Avoid applying heat to the injured area initially, as it can increase swelling.\n" +
                        "    Don't ignore persistent pain, swelling, or difficulty in moving the affected area.\n" +
                        "    Avoid massaging or applying pressure directly to the injured area.\n" +
                        "    Don't delay seeking medical help for fractures, dislocations, or severe injuries."
        ));

        items.add(new Item("Burns",
                "Burns occur when the skin or other tissues are damaged by heat, chemicals, electricity, or radiation.",
                R.drawable.ic_burn,
                "MAJOR",
                "https://www.wikihow.com/Treat-a-Burn",
                "https://youtu.be/Dsvtzwp4nG8",
                "Any part of the body",
                "110011",
                "    Cool the burn under cool running water for at least 10 minutes.\n" +
                        "    Remove any clothing or jewelry near the burn (if it's not stuck to the skin).\n" +
                        "    Cover the burn with a sterile non-stick dressing or clean cloth.\n" +
                        "    Don't apply creams, ointments, or adhesive bandages to the burn.\n" +
                        "    Seek medical help for severe burns, burns on the face, hands, feet, or genitals, or if the burn becomes infected.",
                "    Don't use ice or very cold water on the burn.\n" +
                        "    Avoid breaking any blisters that may have formed.\n" +
                        "    Don't apply adhesive bandages directly to the burn.\n" +
                        "    Avoid using adhesive tape on the burn.\n" +
                        "    Don't use any creams, ointments, or home remedies on the burn."
        ));

        items.add(new Item("Chest Pain",
                "Chest pain can have various causes, including heart-related issues, muscle strain, digestive problems, or anxiety.",
                R.drawable.ic_chest,
                "MAJOR",
                "https://www.wikihow.com/Stop-Chest-Pain",
                "https://youtu.be/gDwt7dD3awc",
                "Chest",
                "110011",
                "    Encourage the person to rest in a comfortable position.\n" +
                        "    Help them take their prescribed heart medication (if available).\n" +
                        "    If the pain persists or worsens, call emergency services.\n" +
                        "    Stay with the person and offer reassurance until medical help arrives.",
                "    Don't ignore persistent or severe chest pain.\n" +
                        "    Avoid giving the person anything to eat or drink.\n" +
                        "    Don't administer aspirin or any other medication unless prescribed.\n" +
                        "    Avoid leaving the person alone if their condition worsens.\n" +
                        "    Don't delay in seeking medical help for chest pain."
        ));

        items.add(new Item("Choking",
                "Choking occurs when the airway is partially or completely blocked, preventing proper breathing.",
                R.drawable.ic_choking1,
                "MAJOR",
                "https://www.wikihow.com/Help-a-Choking-Victim",
                "https://youtu.be/SqpcTF2HFvg",
                "Throat",
                "110011",
                "    Encourage the person to cough forcefully to try to clear the blockage.\n" +
                        "    Perform back blows and abdominal thrusts if the person can't breathe, cough, or speak.\n" +
                        "    Call emergency services if the person becomes unconscious.\n" +
                        "    Continue providing assistance until medical help arrives.",
                "    Don't encourage the person to drink water or other liquids.\n" +
                        "    Avoid blind finger sweeps to remove the blockage.\n" +
                        "    Don't delay in seeking medical help if the person's condition worsens.\n" +
                        "    Avoid forceful slaps on the back if the person can cough or speak.\n" +
                        "    Don't leave the person alone if they are choking."
        ));

        items.add(new Item("Drowning",
                "Drowning occurs when a person's airway is blocked, preventing them from breathing. It often happens in water-related accidents or incidents.",
                R.drawable.ic_drowning,
                "MAJOR",
                "https://www.wikihow.com/Save-an-Active-Drowning-Victim",
                "https://youtu.be/Hlrbio-NpxQ",
                "Water-related",
                "110011",
                "    Call emergency services immediately.\n" +
                        "    Remove the person from the water if safe to do so.\n" +
                        "    Start rescue breaths and chest compressions (CPR) if the person is not breathing.\n" +
                        "    Continue CPR until professional help arrives or the person starts breathing.",
                "    Don't delay calling emergency services in a drowning incident.\n" +
                        "    Avoid moving the person's neck or spine if a spinal injury is suspected.\n" +
                        "    Don't stop performing CPR unless the person starts breathing or professional help arrives.\n" +
                        "    Avoid giving the person food, drink, or any medication.\n" +
                        "    Don't leave the person alone if their condition worsens."
        ));

        items.add(new Item("Head Injury",
                "A head injury refers to any trauma or damage to the head, scalp, skull, or brain.",
                R.drawable.ic_head_injury,
                "MAJOR",
                "https://www.wikihow.com/Treat-a-Mild-Concussion",
                "https://youtu.be/Qdb8xnErnXk",
                "Head",
                "110011",
                "    Encourage the person to sit still and not move excessively.\n" +
                        "    Apply a cold compress to the injured area to reduce swelling.\n" +
                        "    If there are signs of severe head injury, call emergency services immediately.\n" +
                        "    Monitor the person's condition closely and seek medical help if symptoms worsen.",
                "    Don't let the person continue with any physical activities if they have symptoms of a head injury.\n" +
                        "    Avoid giving the person any medication without professional advice.\n" +
                        "    Don't delay seeking medical help if symptoms worsen or if there are signs of severe head injury.\n" +
                        "    Avoid moving the person unnecessarily if a spinal injury is suspected.\n" +
                        "    Don't leave the person alone if their condition deteriorates."
        ));

        items.add(new Item("Poisoning",
                "Poisoning occurs when harmful substances or toxins are ingested, inhaled, or absorbed through the skin.",
                R.drawable.ic_poisning,
                "MAJOR",
                "https://www.wikihow.com/Treat-Poisoning",
                "https://youtu.be/0qwpz43XdVc",
                "Depends on the poisonous substance",
                "110011",
                "    Call emergency services or local poison control immediately.\n" +
                        "    Remove the person from the source of poisoning (if safe to do so).\n" +
                        "    If the person is unconscious but breathing, place them in the recovery position.\n" +
                        "    Follow any specific instructions provided by the poison control or emergency services.",
                "    Don't induce vomiting unless instructed by poison control or medical professionals.\n" +
                        "    Avoid giving the person anything to eat or drink unless advised by professionals.\n" +
                        "    Don't delay seeking medical help or calling poison control.\n" +
                        "    Avoid handling any poisonous substances without proper protection.\n" +
                        "    Don't leave the person alone if their condition worsens."
        ));

        items.add(new Item("Resuscitation",
                "Resuscitation, also known as cardiopulmonary resuscitation (CPR), is a life-saving technique used to restore breathing and circulation in a person who is in cardiac arrest.",
                R.drawable.ic_res   ,
                "MAJOR",
                "https://www.wikihow.com/Do-CPR",
                "https://youtu.be/G87knTZnhks",
                "N/A",
                "110011",
                "    Check for responsiveness and call emergency services.\n" +
                        "    Start chest compressions at a rate of 100-120 compressions per minute.\n" +
                        "    Give rescue breaths (if trained) after every 30 compressions.\n" +
                        "    Continue performing CPR until professional help arrives or the person starts breathing.",
                "    Avoid delaying the start of chest compressions.\n" +
                        "    Don't stop performing CPR unless the person starts breathing or professional help arrives.\n" +
                        "    Avoid excessive interruptions in chest compressions.\n" +
                        "    Don't give rescue breaths if you are not trained or comfortable doing so.\n" +
                        "    Don't leave the person alone if their condition worsens."
        ));

        items.add(new Item("Seizure",
                "A seizure is a sudden, uncontrolled electrical disturbance in the brain that can cause changes in behavior, movements, and consciousness.",
                R.drawable.seizure,
                "MAJOR",
                "https://www.wikihow.com/Help-Someone-Who-Is-Having-a-Seizure",
                "https://youtu.be/Ovsw7tdneqE",
                "Depends on the type of seizure",
                "110011",
                "    Stay with the person and clear the area of any objects that could cause harm.\n" +
                        "    Protect the person's head with something soft, if possible.\n" +
                        "    After the seizure, help the person into a safe position and provide reassurance.\n" +
                        "    If the seizure lasts longer than 5 minutes or if it's their first seizure, call emergency services.",
                "    Don't try to restrain the person or stop their movements.\n" +
                        "    Avoid putting anything in the person's mouth during the seizure.\n" +
                        "    Don't try to wake the person up or offer food or drink immediately after the seizure.\n" +
                        "    Avoid leaving the person alone if their condition worsens.\n" +
                        "    Don't assume the person knows what happened during the seizure; provide necessary information if asked."
        ));

        items.add(new Item("Stroke",
                "A stroke occurs when blood flow to the brain is interrupted, leading to damage to brain cells. It can result in various symptoms, such as weakness, numbness, and difficulty speaking.",
                R.drawable.ic_stoke,
                "MAJOR",
                "https://www.wikihow.com/Prevent-Stroke",
                "https://youtu.be/BR9OjTiwKSM",
                "Brain",
                "110011",
                "    Recognize the signs of a stroke using the FAST acronym: Face drooping, Arm weakness, Speech difficulty, Time to call emergency services.\n" +
                        "    Help the person lie down in a comfortable position with their head slightly raised.\n" +
                        "    Offer reassurance and keep the person calm while waiting for medical help.\n" +
                        "    Note the time of onset of symptoms, as it is crucial information for medical professionals.",
                "    Don't ignore or downplay the symptoms of a possible stroke.\n" +
                        "    Avoid giving the person anything to eat or drink, as they may have difficulty swallowing.\n" +
                        "    Don't attempt to drive the person to the hospital; call emergency services instead.\n" +
                        "    Avoid administering any medication without professional advice.\n" +
                        "    Don't leave the person alone if their condition worsens."
        ));

        items.add(new Item("Allergic Reaction",
                "An allergic reaction occurs when the immune system overreacts to a substance, resulting in symptoms such as itching, swelling, rash, or difficulty breathing.",
                R.drawable.ic_allergic,
                "MINOR",
                "https://www.wikihow.com/Deal-With-Allergic-Reactions",
                "https://youtu.be/bIqNTaRZnhs",
                "Depends on the allergen",
                "110011",
                "    Help the person move away from the allergen, if possible.\n" +
                        "    If they have an auto-injector (e.g., EpiPen) for severe allergies, assist them in using it.\n" +
                        "    Offer reassurance and help the person stay calm.\n" +
                        "    Call emergency services if the symptoms worsen or if the person has difficulty breathing.",
                "    Avoid giving the person anything to eat or drink if they are experiencing difficulty swallowing.\n" +
                        "    Don't assume the person is fine even if the symptoms subside; medical attention may still be necessary.\n" +
                        "    Don't delay calling emergency services if the symptoms worsen rapidly.\n" +
                        "    Avoid applying creams or lotions to the affected area without medical advice.\n" +
                        "    Don't leave the person alone if their condition worsens."
        ));

        items.add(new Item("Cold",
                "A cold is a common viral infection that affects the nose, throat, and upper respiratory system, causing symptoms such as a runny nose, cough, sore throat, and congestion.",
                R.drawable.ic_cold,
                "MINOR",
                "https://www.wikihow.com/Treat-a-Cold",
                "https://youtu.be/eeeVSHgfCZ0",
                "Nose, throat, respiratory system",
                "110011",
                "    Encourage the person to rest and drink plenty of fluids.\n" +
                        "    Provide over-the-counter cold remedies or pain relievers, if appropriate.\n" +
                        "    Help relieve symptoms with warm drinks, steam inhalation, and saline nasal drops.\n" +
                        "    Advise the person to practice good hygiene, such as covering their mouth and nose when coughing or sneezing.",
                "    Don't give antibiotics unless prescribed by a healthcare professional (colds are usually caused by viruses).\n" +
                        "    Avoid exposing others to the cold virus by practicing proper respiratory hygiene.\n" +
                        "    Don't ignore worsening symptoms or signs of complications.\n" +
                        "    Avoid giving aspirin to children or teenagers with a cold (risk of Reye's syndrome).\n" +
                        "    Don't hesitate to seek medical advice if symptoms persist or worsen."
        ));

        items.add(new Item("Diabetes",
                "Diabetes is a chronic condition that affects how the body processes blood sugar (glucose). It can lead to high or low blood sugar levels and various symptoms.",
                R.drawable.ic_diabetes,
                "MINOR",
                "https://www.wikihow.com/Control-Diabetes",
                "https://youtu.be/_gQNm_1wjGk",
                "N/A",
                "110011",
                "    Offer the person a sugary drink or food if they are experiencing low blood sugar (hypoglycemia).\n" +
                        "    Help the person find their glucose monitoring device or insulin if needed.\n" +
                        "    If the person is unresponsive or unable to swallow, call emergency services.",
                "    Don't give the person insulin if they are experiencing high blood sugar (hyperglycemia) without medical advice.\n" +
                        "    Avoid leaving the person alone if their condition worsens.\n" +
                        "    Don't assume the person has diabetes based on symptoms alone; seek medical confirmation.\n" +
                        "    Avoid providing food or drink to an unresponsive person.\n" +
                        "    Don't hesitate to call emergency services if the person's condition is severe or deteriorating."
        ));

        items.add(new Item("Faint",
                "Fainting, also known as syncope, is a temporary loss of consciousness caused by a brief interruption in the brain's blood supply. It can occur due to various factors.",
                R.drawable.ic_faint,
                "MINOR",
                "https://www.wikihow.com/Deal-With-Fainting",
                "https://youtu.be/ddHKwkMwNyI",
                "N/A",
                "110011",
                "    Help the person lie down flat on their back, preferably with their legs elevated.\n" +
                        "    Loosen any tight clothing and ensure adequate ventilation.\n" +
                        "    If the person regains consciousness, offer them fluids and reassurance.",
                "    Avoid assuming the person will be fine after regaining consciousness; monitor them for further symptoms.\n" +
                        "    Don't try to wake the person abruptly or force them to sit or stand.\n" +
                        "    Avoid leaving the person alone if their condition worsens or if they remain unconscious.\n" +
                        "    Don't hesitate to seek medical advice if the person has recurrent fainting episodes or if they are injured during the fall.\n" +
                        "    Don't attempt to move the person unless they are in immediate danger."
        ));

        items.add(new Item("Heat Exhaustion",
                "Heat exhaustion occurs when the body overheats and can't cool itself down properly. It can be caused by prolonged exposure to high temperatures and inadequate fluid intake.",
                R.drawable.ic_heat,
                "MINOR",
                "https://www.wikihow.com/Treat-Heatstroke",
                "https://youtu.be/R6VdoV8dZRc",
                "N/A",
                "110011",
                "    Move the person to a cooler area and encourage them to rest.\n" +
                        "    Provide cool drinks or water for rehydration.\n" +
                        "    Help the person cool down by applying damp towels or using a fan.\n" +
                        "    If the person's condition worsens or doesn't improve, seek medical help.",
                "    Avoid offering alcoholic or caffeinated beverages, as they can contribute to dehydration.\n" +
                        "    Don't let the person overexert themselves or continue activities in the heat.\n" +
                        "    Avoid leaving the person alone if their condition worsens or if they become unresponsive.\n" +
                        "    Don't ignore signs of severe heat-related illness, such as confusion, seizures, or loss of consciousness.\n" +
                        "    Don't delay seeking medical help if the person's condition doesn't improve."
        ));

        items.add(new Item("Hyperventilation",
                "Hyperventilation is an abnormal breathing pattern that involves rapid or deep breathing, often triggered by stress or anxiety. It can lead to various symptoms.",
                R.drawable.ic_hyperventilation,
                "MINOR",
                "https://www.wikihow.health/Prevent-Hyperventilation",
                "https://youtu.be/p97HeXx0vN0",
                "N/A",
                "110011",
                "    Help the person sit down and encourage them to breathe slowly and deeply.\n" +
                        "    Offer reassurance and try to calm the person down.\n" +
                        "    If symptoms persist or worsen, seek medical advice.",
                "    Avoid telling the person to breathe into a paper bag; this technique is no longer recommended.\n" +
                        "    Don't disregard the person's symptoms or assume they will go away on their own.\n" +
                        "    Avoid leaving the person alone if their condition worsens or if they become unresponsive.\n" +
                        "    Don't hesitate to seek medical help if the person is experiencing severe or prolonged symptoms.\n" +
                        "    Don't try to forcefully control or restrict the person's breathing."
        ));

        items.add(new Item("Bites and Stings",
                "Bites and stings from insects, spiders, or animals can cause pain, swelling, itching, and allergic reactions. The severity and treatment depend on the type of bite or sting.",
                R.drawable.ic_bites,
                "MINOR",
                "https://www.wikihow.com/Identify-Insect-Bites",
                "https://youtu.be/ibsIs9-wM1Y",
                "Depends on the type of bite or sting",
                "110011",
                "    Remove any stingers or foreign objects from the skin, if visible.\n" +
                        "    Wash the affected area with soap and water.\n" +
                        "    Apply a cold compress to reduce swelling and pain.\n" +
                        "    If necessary, use over-the-counter pain relievers or antihistamines for symptom relief.",
                "    Avoid aggravating the affected area by scratching or rubbing it.\n" +
                        "    Don't use tweezers or other tools to remove a stinger; use a scraping motion with a credit card or similar object.\n" +
                        "    Avoid applying heat to the affected area, as it can worsen symptoms.\n" +
                        "    Don't hesitate to seek medical attention if there are signs of an allergic reaction or if the bite or sting is from a dangerous species.\n" +
                        "    Don't ignore symptoms that worsen or persist despite home care."
        ));

        items.add(new Item("Earthquakes",
                "Earthquakes are natural disasters that occur when there is a sudden release of energy in the Earth's crust, resulting in seismic waves. They can cause shaking, ground rupture, and various secondary hazards.",
                R.drawable.ic_earthquackes,
                "DISASTERS",
                "https://www.wikihow.com/Survive-an-Earthquake",
                "https://youtu.be/BLEPakj1YTY",
                "N/A",
                "110011",
                "    Drop down to the ground, take cover under a sturdy piece of furniture, and hold on.\n" +
                        "    Stay indoors and away from windows, glass, and heavy objects.\n" +
                        "    If outdoors, move to an open area away from buildings, trees, and power lines.\n" +
                        "    After the shaking stops, be cautious of potential aftershocks and check for injuries.",
                "    Avoid using elevators during or after an earthquake.\n" +
                        "    Don't rush outside during the shaking; it's safer to take cover until the shaking stops.\n" +
                        "    Avoid standing near windows, glass, or other objects that may shatter or fall.\n" +
                        "    Don't touch downed power lines or damaged electrical equipment.\n" +
                        "    Don't enter damaged buildings or areas at risk of collapse without professional guidance."
        ));

        items.add(new Item("Landslides",
                "Landslides occur when masses of rock, earth, or debris move downhill, often triggered by heavy rainfall, earthquakes, or human activities. They can pose significant risks to people and infrastructure.",
                R.drawable.ic_landslide,
                "DISASTERS",
                "https://www.wikihow.com/Be-Safe-During-a-Landslide",
                "https://youtu.be/bX7z102Kq-Q",
                "N/A",
                "110011",
                "    If indoors, stay inside on the lower floors and away from windows and doors.\n" +
                        "    If outdoors, move to higher ground or a sturdy shelter.\n" +
                        "    Be alert for signs of slope movement, such as tilting trees or cracking ground.\n" +
                        "    After a landslide, be cautious of further slope instability and seek medical help for injuries.",
                "    Avoid staying in or entering areas susceptible to landslides during heavy rainfall or after an earthquake.\n" +
                        "    Don't ignore signs of slope movement or unusual sounds from the ground.\n" +
                        "    Avoid crossing or walking on slopes that appear unstable.\n" +
                        "    Don't attempt to drive through landslide-prone areas.\n" +
                        "    Don't return to affected areas until authorities deem it safe."
        ));

        items.add(new Item("Floods",
                "Floods occur when there is an overflow of water onto normally dry land, often caused by heavy rain, melting snow, or dam failure. They can result in property damage, displacement, and other hazards.",
                R.drawable.ic_flood,
                "DISASTERS",
                "https://www.wikihow.com/Survive-a-Flood",
                "https://youtu.be/dvuGyLZh-og",
                "N/A",
                "110011",
                "    Move to higher ground and away from flood-prone areas.\n" +
                        "    Avoid walking or driving through flooded areas.\n" +
                        "    If trapped by rising water, seek higher elevation and call for help.\n" +
                        "    After a flood, be cautious of contaminated water, electrical hazards, and structural damage.",
                "    Avoid crossing fast-moving water or floodwaters of unknown depth.\n" +
                        "    Don't ignore evacuation orders; prioritize your safety and follow official instructions.\n" +
                        "    Avoid contact with floodwater, as it may be contaminated by sewage or chemicals.\n" +
                        "    Don't use electrical equipment or appliances that have been in contact with water until deemed safe.\n" +
                        "    Don't enter damaged buildings or structures without professional assessment."
        ));

        items.add(new Item("Tsunamis",
                "Tsunamis are large ocean waves triggered by earthquakes, volcanic eruptions, or underwater landslides. They can cause widespread destruction and pose significant threats to coastal areas.",
                R.drawable.ic_tsunami,
                "DISASTERS",
                "https://www.wikihow.com/Survive-a-Tsunami",
                "https://youtu.be/m7EDddq9ftQ",
                "N/A",
                "110011",
                "    If near the coast, move inland to higher ground immediately.\n" +
                        "    Follow evacuation routes and instructions provided by local authorities.\n" +
                        "    Stay tuned to official alerts and emergency broadcasts for updates.\n" +
                        "    After a tsunami, be cautious of potential aftershocks and follow guidance from authorities.",
                "    Avoid staying or returning to coastal areas during tsunami warnings or after strong earthquakes.\n" +
                        "    Don't go to the shore to observe or collect items as the first wave may not be the largest.\n" +
                        "    Avoid entering floodwaters or crossing bridges that may have been damaged.\n" +
                        "    Don't rely solely on sirens; stay informed through multiple sources of information.\n" +
                        "    Don't delay or ignore evacuation orders; prioritize your safety and the safety of others."
        ));

        items.add(new Item("Cyclones",
                "Cyclones, also known as hurricanes or typhoons, are powerful tropical storms characterized by strong winds and heavy rain. They can cause extensive damage to infrastructure and pose risks to lives.",
                R.drawable.ic_cyclones,
                "DISASTERS",
                "https://www.wikihow.com/Survive-a-Hurricane",
                "https://youtu.be/aEkAzbM4_HU",
                "N/A",
                "110011",
                "    Stay indoors in a secure location away from windows and doors.\n" +
                        "    Follow official advice and evacuation orders issued by local authorities.\n" +
                        "    Prepare emergency supplies and secure loose objects around your property.\n" +
                        "    After the cyclone passes, be cautious of hazards such as fallen power lines and debris.",
                "    Avoid going outside or attempting to travel during the cyclone, as conditions can be extremely dangerous.\n" +
                        "    Don't disregard official warnings or underestimate the potential impact of the cyclone.\n" +
                        "    Avoid using open flames or candles if power outages occur; opt for battery-powered light sources.\n" +
                        "    Don't return to affected areas until authorities deem it safe.\n" +
                        "    Don't touch or approach fallen power lines or attempt to repair electrical damage."
        ));

        items.add(new Item("Fire",
                "Fires can occur due to various causes, including accidents, natural disasters, or human activities. They pose immediate dangers to life, property, and the environment.",
                R.drawable.ic_fire,
                "DISASTERS",
                "https://www.wikihow.com/Keep-Safe-During-a-House-Fire",
                "https://youtu.be/apwK7Y362qU",
                "N/A",
                "110011",
                "    Call the emergency services immediately to report the fire.\n" +
                        "    Evacuate the area following established escape routes and assembly points.\n" +
                        "    If trapped, find a room with a window and close the door; use towels or clothing to block smoke.\n" +
                        "    After escaping a fire, seek medical attention for injuries and avoid re-entering the building.",
                "    Don't attempt to extinguish a large or spreading fire without proper training and equipment.\n" +
                        "    Avoid using elevators during a fire; use stairs whenever possible.\n" +
                        "    Don't re-enter a burning building to retrieve belongings; prioritize your safety.\n" +
                        "    Don't ignore fire alarms or fail to report a fire; early detection is crucial.\n" +
                        "    Don't underestimate the potential hazards of smoke inhalation; seek medical help if necessary."
        ));

        items.add(new Item("Assessing the Situation",
                "Assessing the situation involves evaluating the surroundings and circumstances to determine the appropriate course of action in a medical emergency. It helps gather information, identify hazards, and prioritize care.",
                R.drawable.ic_asssessing,
                "TECHNIQUES",
                "https://www.wikihow.com/Handle-an-Emergency-Situation",
                "https://youtu.be/ea1RJUOiNfQ",
                "N/A",
                "110011",
                "    Stay calm and observe the scene to identify potential dangers.\n" +
                        "    Assess the injured person's condition and check for responsiveness.\n" +
                        "    Call emergency services if needed and provide accurate information.\n" +
                        "    Take necessary steps to ensure personal safety and the safety of others.",
                "    Avoid rushing into the situation without assessing potential risks.\n" +
                        "    Don't provide care without ensuring your safety and the safety of others.\n" +
                        "    Don't hesitate to call emergency services if the situation requires professional assistance.\n" +
                        "    Don't disregard the importance of accurate information when seeking help or providing updates."
        ));

        items.add(new Item("Treating Shock",
                "Shock is a life-threatening condition that can occur due to various reasons, including severe injuries, medical emergencies, or emotional stress. Prompt treatment is necessary to help stabilize the person's condition.",
                R.drawable.ic_treating_shock,
                "TECHNIQUES",
                "https://www.wikihow.com/Treat-Shock",
                "https://youtu.be/61urGQrmeNM",
                "N/A",
                "110011",
                "    Ensure the person is lying flat on their back.\n" +
                        "    Elevate their legs, if possible, to improve blood flow.\n" +
                        "    Maintain a stable body temperature and cover the person with a blanket.\n" +
                        "    Reassure and support the person until medical help arrives.",
                "    Don't give the person anything to eat or drink, as they may require medical intervention.\n" +
                        "    Avoid unnecessary movement or jostling of the person, as it can worsen their condition.\n" +
                        "    Don't delay seeking professional medical assistance for a person in shock.\n" +
                        "    Don't assume the person will recover on their own; immediate medical attention is crucial."
        ));

        items.add(new Item("Hygiene",
                "Maintaining proper hygiene practices is essential for preventing the spread of infections, diseases, and maintaining overall health. It includes regular handwashing, cleanliness, and adopting hygienic habits.",
                R.drawable.ic_hygine,
                "TECHNIQUES",
                "https://www.wikihow.com/Be-Hygienic",
                "https://youtu.be/UxskKQ9WOTE",
                "N/A",
                "110011",
                "    Wash your hands thoroughly with soap and water for at least 20 seconds.\n" +
                        "    Cover your mouth and nose with a tissue or your elbow when coughing or sneezing.\n" +
                        "    Avoid touching your face, especially eyes, nose, and mouth.\n" +
                        "    Clean and disinfect frequently-touched objects and surfaces regularly.",
                "    Don't neglect proper handwashing, as it is a crucial step in preventing the spread of infections.\n" +
                        "    Avoid close contact with people who are sick or showing symptoms of illness.\n" +
                        "    Don't ignore personal cleanliness and hygiene practices for maintaining overall health.\n" +
                        "    Don't forget to dispose of used tissues properly and wash your hands afterward.\n" +
                        "    Don't overlook the importance of regularly cleaning and disinfecting commonly-touched surfaces."
        ));

        items.add(new Item("Using an AED (Automated External Defibrillator)",
                "An AED is a portable device used to deliver an electric shock to the heart during sudden cardiac arrest. It helps restore the heart's normal rhythm and can significantly improve the chances of survival.",
                R.drawable.ic_usingaed,
                "TECHNIQUES",
                "https://www.wikihow.com/Use-a-Defibrillator",
                "https://youtu.be/0Yb4RA8C5D8",
                "N/A",
                "110011",
                "    Turn on the AED and follow the voice prompts or visual instructions provided.\n" +
                        "    Apply the pads to the person's bare chest as directed.\n" +
                        "    Clear everyone from the person and ensure no one is touching the body during analysis and shock delivery.\n" +
                        "    Follow the AED prompts for delivering a shock if advised.",
                "    Avoid using an AED on a person who is responsive and breathing normally.\n" +
                        "    Don't touch the person or the AED during analysis or shock delivery.\n" +
                        "    Don't delay in calling emergency services and starting CPR before using the AED.\n" +
                        "    Don't use an AED on a person with a pacemaker or implantable cardioverter-defibrillator (ICD).\n" +
                        "    Don't hesitate to follow the AED's instructions and ask for help if needed."
        ));

        items.add(new Item("Opening Airways",
                "Opening the airways is a critical step in providing first aid for a person who is unconscious or having difficulty breathing. It helps ensure the airway is clear and facilitates adequate oxygen supply.",
                R.drawable.ic_openingairways,
                "TECHNIQUES",
                "https://www.wikihow.com/Check-Airway,-Breathing-and-Circulation",
                "https://youtu.be/2fnS8mtqzms",
                "N/A",
                "110011",
                "    Place one hand on the person's forehead and gently tilt their head back.\n" +
                        "    Lift their chin using two fingers of your other hand to open the airway.\n" +
                        "    Look, listen, and feel for signs of breathing.\n" +
                        "    If not breathing, provide rescue breaths using mouth-to-mouth or mouth-to-nose technique.",
                "    Avoid forcefully tilting the person's head or overextending their neck, as it may cause injury.\n" +
                        "    Don't assume the person is breathing normally without performing a thorough check.\n" +
                        "    Don't hesitate to seek medical assistance if the person remains unresponsive or stops breathing.\n" +
                        "    Don't delay in providing rescue breaths if the person is not breathing.\n" +
                        "    Don't forget to recheck the person's breathing periodically while waiting for medical help."
        ));

        items.add(new Item("Recovery Position",
                "The recovery position is a safe and stable position used to place an unconscious person who is breathing normally. It helps protect the airway and prevent the risk of aspiration.",
                R.drawable.recovery_position,
                "TECHNIQUES",
                "https://www.wikihow.com/Put-Someone-in-the-Recovery-Position",
                "https://youtu.be/GmqXqwSV3bo",
                "N/A",
                "110011",
                "    Kneel beside the person and place their arm that is closest to you at a right angle to their body, with the elbow bent.\n" +
                        "    Bring their other arm across their chest and place the back of their hand against their cheek on the side farthest from you.\n" +
                        "    Bend the knee that is farthest from you to a right angle.\n" +
                        "    Carefully roll the person onto their side by pulling their bent knee towards you.",
                "    Don't attempt to place an unconscious person in the recovery position if they are not breathing normally.\n" +
                        "    Avoid twisting or jerking the person's body while moving them into the recovery position.\n" +
                        "    Don't forget to monitor the person's breathing and be prepared to provide CPR if needed.\n" +
                        "    Don't leave the person unattended in the recovery position; seek medical help as soon as possible.\n" +
                        "    Don't use excessive force or rough handling when moving the person into the recovery position."
        ));







        // Initialize the adapter
        myAdapter = new MyAdapter(getApplicationContext(), items);

        // Set the adapter to the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


        //when click on major
        activityFirstAidTipsTwoBinding.majorCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                activityFirstAidTipsTwoBinding.allCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.minorCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.disasterCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.techCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.majorCon.setBackgroundResource(R.drawable.post_light_blue);

                activityFirstAidTipsTwoBinding.allTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.minorTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.techTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.disasterTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.majorTt.setTextColor(getColor(R.color.Primary_box));




                ArrayList<Item> filteredList = new ArrayList<>();

                for (Item item : items) {
                    if (item.getType().toLowerCase().contains("major") ){
                        filteredList.add(item);
                    }
                }

                myAdapter.filterList(filteredList);
            }
        });

        //click on minor
        activityFirstAidTipsTwoBinding.minorCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                activityFirstAidTipsTwoBinding.allCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.majorCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.disasterCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.techCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.minorCon.setBackgroundResource(R.drawable.post_light_blue);

                activityFirstAidTipsTwoBinding.allTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.majorTt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.techTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.disasterTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.minorTxt.setTextColor(getColor(R.color.Primary_box));




                ArrayList<Item> filteredList = new ArrayList<>();

                for (Item item : items) {
                    if (item.getType().toLowerCase().contains("minor") ){
                        filteredList.add(item);
                    }
                }

                myAdapter.filterList(filteredList);

            }
        });


        activityFirstAidTipsTwoBinding.disasterCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityFirstAidTipsTwoBinding.allCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.minorCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.majorCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.techCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.disasterCon.setBackgroundResource(R.drawable.post_light_blue);

                activityFirstAidTipsTwoBinding.allTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.minorTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.techTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.majorTt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.disasterTxt.setTextColor(getColor(R.color.Primary_box));




                ArrayList<Item> filteredList = new ArrayList<>();

                for (Item item : items) {
                    if (item.getType().toLowerCase().contains("disasters") ){
                        filteredList.add(item);
                    }
                }

                myAdapter.filterList(filteredList);

            }
        });


        activityFirstAidTipsTwoBinding.techCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityFirstAidTipsTwoBinding.allCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.minorCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.disasterCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.majorCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.techCon.setBackgroundResource(R.drawable.post_light_blue);

                activityFirstAidTipsTwoBinding.allTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.minorTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.majorTt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.disasterTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.techTxt.setTextColor(getColor(R.color.Primary_box));




                ArrayList<Item> filteredList = new ArrayList<>();

                for (Item item : items) {
                    if (item.getType().toLowerCase().contains("techniques") ){
                        filteredList.add(item);
                    }
                }

                myAdapter.filterList(filteredList);

            }
        });


        activityFirstAidTipsTwoBinding.allCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityFirstAidTipsTwoBinding.majorCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.minorCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.disasterCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.techCon.setBackgroundResource(R.drawable.diable_post);
                activityFirstAidTipsTwoBinding.allCon.setBackgroundResource(R.drawable.post_light_blue);

                activityFirstAidTipsTwoBinding.majorTt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.minorTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.techTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.disasterTxt.setTextColor(getColor(R.color.text_primary));
                activityFirstAidTipsTwoBinding.allTxt.setTextColor(getColor(R.color.Primary_box));




                ArrayList<Item> filteredList = new ArrayList<>();

                for (Item item : items) {
                    if (item.getType().toLowerCase().contains("") ){
                        filteredList.add(item);
                    }
                }

                myAdapter.filterList(filteredList);

            }
        });

        activityFirstAidTipsTwoBinding.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordSpeech();
            }
        });

            animationFirstAid();

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

    private void animationFirstAid()
    {
        _Animator(activityFirstAidTipsTwoBinding.headFirstAid,"alpha",0,0);
        _Animator(activityFirstAidTipsTwoBinding.searchBarCon,"alpha",0,0);
        _Animator(activityFirstAidTipsTwoBinding.headFirstAid,"translationY",75,0);
        _Animator(activityFirstAidTipsTwoBinding.searchBarCon,"translationY",75,0);
        _Animator(activityFirstAidTipsTwoBinding.allCon,"translationX",75,0);
        _Animator(activityFirstAidTipsTwoBinding.majorCon,"translationX",75,0);
        _Animator(activityFirstAidTipsTwoBinding.minorCon,"translationX",75,0);
        _Animator(activityFirstAidTipsTwoBinding.disasterCon,"translationX",75,0);
        _Animator(activityFirstAidTipsTwoBinding.techCon,"translationX",75,0);
        _Animator(activityFirstAidTipsTwoBinding.allCon,"alpha",0,0);
        _Animator(activityFirstAidTipsTwoBinding.majorCon,"alpha",0,0);
        _Animator(activityFirstAidTipsTwoBinding.minorCon,"alpha",0,0);
        _Animator(activityFirstAidTipsTwoBinding.disasterCon,"alpha",0,0);
        _Animator(activityFirstAidTipsTwoBinding.techCon,"alpha",0,0);
        _Animator(activityFirstAidTipsTwoBinding.conList,"alpha",0,0);
        _Animator(activityFirstAidTipsTwoBinding.conList,"translationY",75,0);


        _Animator(activityFirstAidTipsTwoBinding.headFirstAid,"translationY",0,300);
        _Animator(activityFirstAidTipsTwoBinding.headFirstAid,"alpha",1,300);

        Timer _timer = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.searchBarCon,"translationY",0,300);
                        _Animator(activityFirstAidTipsTwoBinding.searchBarCon,"alpha",1,300);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(175));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.allCon,"translationX",0,250);
                        _Animator(activityFirstAidTipsTwoBinding.allCon,"alpha",1,250);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(350));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.majorCon,"translationX",0,250);
                        _Animator(activityFirstAidTipsTwoBinding.majorCon,"alpha",1,250);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(500));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.minorCon,"translationX",0,250);
                        _Animator(activityFirstAidTipsTwoBinding.minorCon,"alpha",1,250);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(650));


        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.disasterCon,"translationX",0,250);
                        _Animator(activityFirstAidTipsTwoBinding.disasterCon,"alpha",1,250);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(750));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.techCon,"translationX",0,250);
                        _Animator(activityFirstAidTipsTwoBinding.techCon,"alpha",1,250);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(850));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.conList,"translationY",0,300);
                        _Animator(activityFirstAidTipsTwoBinding.conList,"alpha",1,300);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(950));


        //animation list




    }

    private void recordSpeech() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");
        try{
            startActivityForResult(intent,1);
        }
        catch (Exception e ){
            Toast.makeText(this,"Sorry,Something Went Wrong!!",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 )
        {
            if (resultCode==RESULT_OK && data!=null)
            {
                ArrayList<String>text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                activityFirstAidTipsTwoBinding.searchEdit.setText(text.get(0));
            }

        }

    }


    @Override
    public void onBackPressed() {


        _Animator(activityFirstAidTipsTwoBinding.conList,"translationY",75,200);
        _Animator(activityFirstAidTipsTwoBinding.conList,"alpha",0,200);

        Timer _timer = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.techCon,"translationX",75,100);
                        _Animator(activityFirstAidTipsTwoBinding.techCon,"alpha",0,100);
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
                        _Animator(activityFirstAidTipsTwoBinding.disasterCon,"translationX",75,100);
                        _Animator(activityFirstAidTipsTwoBinding.disasterCon,"alpha",0,100);
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
                        _Animator(activityFirstAidTipsTwoBinding.minorCon,"translationX",75,100);
                        _Animator(activityFirstAidTipsTwoBinding.minorCon,"alpha",0,100);
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
                        _Animator(activityFirstAidTipsTwoBinding.majorCon,"translationX",75,100);
                        _Animator(activityFirstAidTipsTwoBinding.majorCon,"alpha",0,100);
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
                        _Animator(activityFirstAidTipsTwoBinding.allCon,"translationX",75,100);
                        _Animator(activityFirstAidTipsTwoBinding.allCon,"alpha",0,100);
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
                        _Animator(activityFirstAidTipsTwoBinding.searchBarCon,"translationY",75,100);
                        _Animator(activityFirstAidTipsTwoBinding.searchBarCon,"alpha",0,100);
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(350));

        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(activityFirstAidTipsTwoBinding.headFirstAid,"translationX",75,100);
                        _Animator(activityFirstAidTipsTwoBinding.headFirstAid,"alpha",0,100);
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

                        first_aid_tips_two.super.onBackPressed();
                    }
                });
            }
        };
        _timer.schedule(timer, (int)(500));

    }
}