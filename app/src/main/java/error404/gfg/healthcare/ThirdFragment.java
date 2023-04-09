package error404.gfg.healthcare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {
    LinearLayoutManager mlinearLayoutManager;
    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mdatabaseReference;
    FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Model> options;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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


        View v = inflater.inflate(R.layout.fragment_third, container, false);

        mlinearLayoutManager = new LinearLayoutManager(getActivity());
        mlinearLayoutManager.setReverseLayout(true);
        mlinearLayoutManager.setStackFromEnd(true);

        mrecyclerView = v.findViewById(R.id.broadcast_recyclerView);
        mfirebaseDatabase=FirebaseDatabase.getInstance();
        mdatabaseReference =mfirebaseDatabase.getReference("broadcast");
        showData();

        return  v;
    }

    private void showData() {

        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(mdatabaseReference,Model.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
                holder.setDeatils(getActivity().getApplicationContext(),model.getImg(), model.getHeadline(), model.getDescription(),model.getPublisher(),model.getTime(),model.getWeblink(),model.getYtlink());
                holder.mview.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.falldown));
                holder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent arc = new Intent(getActivity(), Artical.class);
                        arc.putExtra("image",model.getImg());
                        arc.putExtra("headline",model.getHeadline());
                        arc.putExtra("description",model.getDescription());
                        arc.putExtra("publisher",model.getPublisher());
                        arc.putExtra("time",model.getTime());
                        arc.putExtra("weblink",model.getWeblink());
                        arc.putExtra("ytlink",model.getYtlink());
                        startActivity(arc);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });


            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.broadcast_recycler,parent,false);
               ViewHolder viewHolder = new ViewHolder(itemView);
               viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                   @Override
                   public void onItemClick(View view, int position) {

                       //on click


                   }

                   @Override
                   public void onItemLongClick(View view, int position) {
                       Toast.makeText(getActivity(), "Long CLick ", Toast.LENGTH_SHORT).show();
                   }
               });
                return viewHolder;
            }
        };

        mrecyclerView.setLayoutManager(mlinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    public void onStart()
    {
        super.onStart();
        if(firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }
}