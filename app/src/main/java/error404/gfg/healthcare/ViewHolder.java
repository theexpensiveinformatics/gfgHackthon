package error404.gfg.healthcare;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mview;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mview = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return true;
            }
        });
    }

    public void setDeatils(Context ctx,String img,String headline,String description,String publisher,String time,String weblink,String ytlink)
    {


        TextView mheadline = mview.findViewById(R.id.headtxt);
        TextView mpublisher = mview.findViewById(R.id.publishertxt);
        TextView mtime = mview.findViewById(R.id.timeb);
        ImageView mimg = mview.findViewById(R.id.imageset);
        mheadline.setText(headline);
        mpublisher.setText(publisher);
        mtime.setText(time);
        Picasso.get().load(img).into(mimg);


    }

    private  ViewHolder.ClickListener mClickListener;
    public interface ClickListener {
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int position);

    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener)
    {
        mClickListener=clickListener;

    }
}


//
//public class RecyclerViewAdpter  extends RecyclerView.Adapter<RecyclerViewAdpter.ViewHolder> {
//    private Context context;
//    DatabaseReference reference;
//    final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("broadcast");
//
//    private ArrayList<Model> List;
//
//    public RecyclerViewAdpter(Context context, ArrayList<Model> list) {
//        this.context = context;
//        List = list;
//    }
//
//    public void addModel(Model msg){
//        List.add(0,msg);
//        notifyDataSetChanged();
//    }
//
//
//    @NonNull
//    @Override
//    public RecyclerViewAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.broadcast_recycler,parent,false);
//
//        return new ViewHolder(view);
//
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerViewAdpter.ViewHolder holder, int position) {
//        holder.username.setText(List.get(position).getUserEmail());
//        holder.message.setText(List.get(position).getMessage());
//        holder.dateTime.setText(List.get(position).getDateTime());
//        holder.img_av.setText(List.get(position).getImg_av());
//        holder.card.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.falldown));
//        holder.msg_cons.setBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(),null));
//
//        holder.headtxt.setText(List.get(position).getImg());
//
//
//        //delete items
//        holder.msg_cons.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                deleteMessage(position);
//
//                return true;
//            }
//        });
//
//        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                deleteMessage(position);
//
//                return true;
//            }
//        });
//        holder.username.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                deleteMessage(position);
//
//                return true;
//            }
//        });
//        holder.img_av.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                deleteMessage(position);
//
//                return true;
//            }
//        });
//
//        holder.message.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                deleteMessage(position);
//
//                return true;
//            }
//        });
//        holder.img_av.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                deleteMessage(position);
//
//                return true;
//            }
//        });
//
//
//
//    }
//
//    private void deleteMessage(int position) {
//
//
//        String myUID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
////        String msgTimeStamp = List.get(position).getDateTime();
//        String Msg = List.get(position).getMessage();
//        reference = FirebaseDatabase.getInstance().getReference("Messages");
//        Query query = reference.orderByChild("message").equalTo(Msg);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds: snapshot.getChildren()){
//
//
//                    if(Objects.equals(ds.child("uid").getValue(), myUID))
//                    {
//                        ds.getRef().removeValue();
//                    }else
//                    {
//                        Toast.makeText(context,"Not Authorized Person!",Toast.LENGTH_SHORT).show();
//                    }
//
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(context,"Not Authorized Person!",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private int getRandomColor() {
//        java.util.List<Integer> colorCode=new ArrayList< >();
//        colorCode.add(R.color.one);
//        colorCode.add(R.color.two);
//        colorCode.add(R.color.three);
//        colorCode.add(R.color.four);
//        colorCode.add(R.color.five);
//
//
////         randomColor = new Random();
//        Random randomColor = new Random();
//        int number = randomColor.nextInt(colorCode.size());
//        return  colorCode.get(number);
//    }
//
//    @Override
//    public int getItemCount() {
//        return List.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public Object view;
//        TextView username,message,dateTime,img_av;
//        ImageView prof_img;
//        ConstraintLayout msg_cons;
//        private CardView card;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            prof_img=itemView.findViewById(R.id.prof_img);
//            username=itemView.findViewById(R.id.msg_name);
//            message=itemView.findViewById(R.id.msg_txt);
//            dateTime=itemView.findViewById(R.id.msg_time);
//            img_av=itemView.findViewById(R.id.msg_av);
//            card=itemView.findViewById(R.id.card);
//            msg_cons=itemView.findViewById(R.id.msg_cons);
//
//            String imgav = img_av.getText().toString();
////            if (imgav != null) {
////                if (imgav.equals("Smart Boy")) {
////
////                    prof_img.setImageResource(R.drawable.boy);
////                } else if (imgav.equals("Smart Girl")) {
////                    prof_img.setImageResource(R.drawable.girl);
////                } else if (imgav.equals("Gentlemen")) {
////                    prof_img.setImageResource(R.drawable.gen);
////                } else if (imgav.equals("Rosy")) {
////                    prof_img.setImageResource(R.drawable.spec);
////                }
////            }
//
//        }
//    }
//}

