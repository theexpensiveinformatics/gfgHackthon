package error404.gfg.healthcare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import error404.gfg.healthcare.Token.TokenManager;
import error404.gfg.healthcare.model.UserModel;
import error404.gfg.healthcare.reotrfit.RetrofitService;
import error404.gfg.healthcare.reotrfit.userAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    LinearLayout logoutBg,firstNameBg;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_profile, container, false);
        TextView FristName, LastName, Email,PhoneNumber,Gender,BirthDate,BloodGroup,Address;
        ImageView profileedit;

        FristName = v.findViewById(R.id.FirstNameEdit);
        LastName = v.findViewById(R.id.LastNameEdit);
        Email = v.findViewById(R.id.EmailTxt);
        PhoneNumber = v.findViewById(R.id.NumberTxt);
        Gender = v.findViewById(R.id.GenderTxt);
        BirthDate = v.findViewById(R.id.BirthDateText);
        BloodGroup = v.findViewById(R.id.BloodGrpTxt);
        Address = v.findViewById(R.id.AddressTxt);
        profileedit =v.findViewById(R.id.imageView2);

        //This is for firebase.
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = fAuth.getCurrentUser();
        String uid = firebaseUser.getUid();

        DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ProfileData = DBref.child("users").child(uid);
        ProfileData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FristName.setText(snapshot.child("FirstName").getValue(String.class));
                LastName.setText(snapshot.child("LastName").getValue(String.class));
                Email.setText(snapshot.child("Email").getValue(String.class));
                PhoneNumber.setText(snapshot.child("Number").getValue(String.class));
                Gender.setText(snapshot.child("Gender").getValue(String.class));
                BirthDate.setText(snapshot.child("BirthDate").getValue(String.class));
                BloodGroup.setText(snapshot.child("BloodGroup").getValue(String.class));
                Address.setText(snapshot.child("Address").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//         This is API
//        RetrofitService retrofitService = new RetrofitService();
//        userAPI userAPI = retrofitService.getRetrofit().create(error404.gfg.healthcare.reotrfit.userAPI.class);
//
//        TokenManager tokenManager = TokenManager.getInstance(getActivity());
//        String accessToken = tokenManager.getAccessToken();
//
//        Call<UserModel> call = userAPI.getUserProfile("Bearer " + accessToken);
//
//        call.enqueue(new Callback<UserModel>() {
//            @Override
//            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                if (response.isSuccessful()){
//                    UserModel model = response.body();
//                    FristName.setText(model.getFirstName());
//                    LastName.setText(model.getLastName());
//                    Email.setText(model.getEmail());
//                    PhoneNumber.setText(model.getNumber());
//                    Gender.setText(model.getGender());
//                    BirthDate.setText(model.getBirthDate());
//                    BloodGroup.setText(model.getBloodGroup());
//                    Address.setText(model.getAddress());
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
//;

        profileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileEdit.class));
            }
        });

        firstNameBg = v.findViewById(R.id.firstNameBg);
        firstNameBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutBg=v.findViewById(R.id.logoutBg);
        logoutBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                This is For FireBase

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), MainActivity.class)); //Go back to home page
                getActivity().finish();

//                This is For API
//                TokenManager tokenManager = TokenManager.getInstance(getActivity())
//                tokenManager.clearAccessToken();
//
//                startActivity(new Intent(getActivity(), MainActivity.class)); //Go back to home page
//                getActivity().finish();
            }
        });
        return  v;
    }
}