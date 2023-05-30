package error404.gfg.healthcare;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.Timer;
import java.util.TimerTask;

import error404.gfg.healthcare.Token.TokenManager;
import error404.gfg.healthcare.databinding.ActivityHomeScreen2Binding;
import error404.gfg.healthcare.model.UserModel;
import error404.gfg.healthcare.reotrfit.RetrofitService;
import error404.gfg.healthcare.reotrfit.userAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    LinearLayout ECallCon,instuctor_Con,quiz_con,sym_con,lastCon;
    ImageView imageEme;
    ImageView imageQuiz;
    TextView textView13,user_Name;
    CardView profileCard;
    TextView t3,t4;
    LinearLayout con_sym,con_ins,con_quiz,con_call,con_last;


    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.SEND_SMS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS
    };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = fAuth.getCurrentUser();
        String uid = firebaseUser.getUid();

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        user_Name = v.findViewById(R.id.textView4);
        textView13=v.findViewById(R.id.textView13);
        imageEme=v.findViewById(R.id.imageView23);
        ECallCon = v.findViewById(R.id.call_con);
        imageQuiz=v.findViewById(R.id.imageView22);
        quiz_con= v.findViewById(R.id.quiz_con);
        lastCon=v.findViewById(R.id.last_con);
        sym_con=v.findViewById(R.id.con_sym);
        t3=v.findViewById(R.id.textView3);
        profileCard=v.findViewById(R.id.profile_card);
        instuctor_Con=v.findViewById(R.id.instractor_con);


        _Animator(t3,"translationY",70,0);
        _Animator(user_Name,"translationY",70,0);
        _Animator(profileCard,"translationY",70,0);
        _Animator(sym_con,"translationY",70,0);
        _Animator(instuctor_Con,"translationY",70,0);
        _Animator(quiz_con,"translationY",70,0);
        _Animator(lastCon,"translationY",70,0);
        _Animator(ECallCon,"translationY",70,0);
        _Animator(instuctor_Con,"translationY",70,0);
        _Animator(t3,"alpha",0,0);
        _Animator(user_Name,"alpha",0,0);
        _Animator(profileCard,"alpha",0,0);
        _Animator(sym_con,"alpha",0,0);
        _Animator(instuctor_Con,"alpha",0,0);
        _Animator(quiz_con,"alpha",0,0);
        _Animator(lastCon,"alpha",0,0);
        _Animator(ECallCon,"alpha",0,0);
        _Animator(instuctor_Con,"alpha",0,0);



        Timer _timer = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(t3, "translationY", 0, 200);
                        _Animator(t3, "alpha", 1, 200);
                    }
                });
            }
        };
        _timer.schedule(timer, 100);

        timer = new TimerTask() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(user_Name, "translationY", 0, 150);
                        _Animator(user_Name, "alpha", 1, 150);
                    }
                });
            }
        };
        _timer.schedule(timer, 200);

        timer = new TimerTask() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(profileCard, "translationY", 0, 150);
                        _Animator(profileCard, "alpha", 1, 150);
                    }
                });
            }
        };
        _timer.schedule(timer, 275);

        timer = new TimerTask() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(sym_con, "translationY", 0, 200);
                        _Animator(sym_con, "alpha", 1, 200);
                    }
                });
            }
        };
        _timer.schedule(timer, 375);

        timer = new TimerTask() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(instuctor_Con, "translationY", 0, 200);
                        _Animator(instuctor_Con, "alpha", 1, 200);
                    }
                });
            }
        };
        _timer.schedule(timer, 475);

        timer = new TimerTask() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(quiz_con, "translationY", 0, 200);
                        _Animator(quiz_con, "alpha", 1, 200);
                    }
                });
            }
        };
        _timer.schedule(timer, 575);

        timer = new TimerTask() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(ECallCon, "translationY", 0, 200);
                        _Animator(ECallCon, "alpha", 1, 200);
                    }
                });
            }
        };
        _timer.schedule(timer, 675);

        timer = new TimerTask() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _Animator(lastCon, "translationY", 0, 200);
                        _Animator(lastCon, "alpha", 1, 200);
                    }
                });
            }
        };
        _timer.schedule(timer, 775);

        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ((home_screen_2)getActivity()).activityHomeScreen2Binding.frameLayout.;


            }
        });





        ECallCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ecall = new Intent(getActivity(), ECall.class);
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),imageEme, ViewCompat.getTransitionName(imageEme));

//                Pair[] pairs=new Pair[2];
//                pairs[0]=new Pair<View,String >(textView13,"ecallTxt");
//                pairs[1]=new Pair<View,String>(imageEme,"example");
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),pairs);
                startActivity(ecall);
            }
        });

        DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference NameRef = DBref.child("users").child(uid);
        NameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstName = snapshot.child("FirstName").getValue(String.class);
                String lastName = snapshot.child("LastName").getValue(String.class);
                String name = firstName + " " + lastName;
                user_Name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),""+error,Toast.LENGTH_SHORT).show();
            }
        });


//        RetrofitService retrofitService = new RetrofitService();
//        userAPI userAPI = retrofitService.getRetrofit().create(error404.gfg.healthcare.reotrfit.userAPI.class);
//
//        TokenManager tokenManager = TokenManager.getInstance(getActivity());
//        String accessToken = tokenManager.getAccessToken();
//
//        Call<UserModel> call = userAPI.getUserProfile("Bearer " + accessToken);
//        call.enqueue(new Callback<UserModel>() {
//            @Override
//            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                if(response.isSuccessful()){
//                    user_Name.setText(response.body().getFirstName() + " " + response.body().getLastName());
//                }else {
//                    Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserModel> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });



        //instuctor
        instuctor_Con=v.findViewById(R.id.instractor_con);
        instuctor_Con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructor = new Intent(getActivity(), first_aid_tips_two.class);
                startActivity(instructor);
            }
        });

        //last con

        lastCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (arePermissionsGranted()) {
                    startNextActivity();
                } else {
                    requestPermissions();
                }

            }
        });


        quiz_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(getActivity(), QuizGame.class);
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),imageQuiz, ViewCompat.getTransitionName(imageQuiz));
                startActivity(quizIntent);

            }
        });


        sym_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sym = new Intent(getActivity(), symtopChecker.class);
                startActivity(sym);

            }
        });









        return v;
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSION_REQUEST_CODE);
        } else {
            startNextActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                startNextActivity();
            } else {
                showPermissionDeniedToast();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private boolean arePermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void showPermissionDeniedToast() {
        Toast.makeText(requireContext(), "Permissions denied. Please grant the required permissions.", Toast.LENGTH_SHORT).show();
    }

    private void startNextActivity() {
        Intent nextIntent = new Intent(getActivity(), SosActivity.class);
        startActivity(nextIntent);
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

    private  void replaceFragment(Fragment fragment)
    {
//        FragmentManager fragmentManager  = getSupportFragmentManager();
//        fragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_anim,R.anim.fragment_anim_2);
//        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, fragment);
//        fragmentTransaction.commit();



}}