package error404.gfg.healthcare;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

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

