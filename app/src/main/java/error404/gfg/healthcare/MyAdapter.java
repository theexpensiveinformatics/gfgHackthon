//package error404.gfg.healthcare;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
//   Context context;
//   List<Item>items;
//
//
//   public void setFilteredList(List<Item>filteredList)
//   {
//       this.items = filteredList;
//       notifyDataSetChanged();
//   }
//
//   public MyAdapter(List<Item>filteredList)
//   {
//       this.items=items;
//   }
//
//    public MyAdapter(Context context, List<Item> items) {
//        this.context = context;
//        this.items = items;
//    }
//
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.title.setText(items.get(position).getTitle());
//        holder.description.setText(items.get(position).getDescription());
//        holder.type.setText(items.get(position).getType());
//        holder.img.setImageResource(items.get(position).getImage());
//        holder.effectedPart.setText(items.get(position).getEffectedPart());
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public void filterList(ArrayList<Item> filteredList)
//    {
//        items=filteredList;
//        notifyDataSetChanged();
//    }
//}


package error404.gfg.healthcare;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;

    List<Item> items;

    public void setFilteredList(List<Item> filteredList) {
        this.items = filteredList;
        notifyDataSetChanged();
    }

    public MyAdapter(List<Item> filteredList) {
        this.items = items;
    }

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,
                parent, false);
        MyViewHolder evh = new MyViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.description.setText(items.get(position).getDescription());
        holder.type.setText(items.get(position).getType());
        holder.img.setImageResource(items.get(position).getImage());
        holder.effectedPart.setText(items.get(position).getEffectedPart());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filterList(ArrayList<Item> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView type;
        ImageView img;
        TextView effectedPart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_list);
            description = itemView.findViewById(R.id.des);
            type = itemView.findViewById(R.id.type);
            img = itemView.findViewById(R.id.img);
            effectedPart = itemView.findViewById(R.id.effectedPart);
        }
    }

}
