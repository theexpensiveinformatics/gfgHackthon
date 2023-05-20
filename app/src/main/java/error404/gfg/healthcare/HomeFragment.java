package error404.gfg.healthcare;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

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
    LinearLayout ECallCon,instuctor_Con,quiz_con,sym_con;
    ImageView imageEme;
    ImageView imageQuiz;
    TextView textView13,user_Name;


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

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        user_Name = v.findViewById(R.id.textView4);
        textView13=v.findViewById(R.id.textView13);
        imageEme=v.findViewById(R.id.imageView23);
        ECallCon = v.findViewById(R.id.call_con);
        imageQuiz=v.findViewById(R.id.imageView22);
        quiz_con= v.findViewById(R.id.quiz_con);
        sym_con=v.findViewById(R.id.con_sym);
        ECallCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ecall = new Intent(getActivity(), ECall.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),imageEme, ViewCompat.getTransitionName(imageEme));

//                Pair[] pairs=new Pair[2];
//                pairs[0]=new Pair<View,String >(textView13,"ecallTxt");
//                pairs[1]=new Pair<View,String>(imageEme,"example");
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),pairs);
                startActivity(ecall,options.toBundle());
            }
        });
        RetrofitService retrofitService = new RetrofitService();
        userAPI userAPI = retrofitService.getRetrofit().create(error404.gfg.healthcare.reotrfit.userAPI.class);

        TokenManager tokenManager = TokenManager.getInstance(getActivity());
        String accessToken = tokenManager.getAccessToken();

        Call<UserModel> call = userAPI.getUserProfile("Bearer " + accessToken);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    user_Name.setText(response.body().getFirstName() + " " + response.body().getLastName());
                }else {
                    Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        //instuctor
        instuctor_Con=v.findViewById(R.id.instractor_con);
        instuctor_Con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructor = new Intent(getActivity(), first_aid_tips_two.class);
                startActivity(instructor);
            }
        });


        quiz_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(getActivity(), QuizGame.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),imageQuiz, ViewCompat.getTransitionName(imageQuiz));
                startActivity(quizIntent,options.toBundle());

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
}