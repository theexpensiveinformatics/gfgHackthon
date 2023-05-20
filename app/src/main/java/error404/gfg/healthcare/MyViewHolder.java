package error404.gfg.healthcare;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView title, description,type,effectedPart;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        img = itemView.findViewById(R.id.img);
        title = itemView.findViewById(R.id.title_list);
        description =itemView.findViewById(R.id.des);
        type =itemView.findViewById(R.id.type);
        effectedPart=itemView.findViewById(R.id.effectedPart);

    }
}
