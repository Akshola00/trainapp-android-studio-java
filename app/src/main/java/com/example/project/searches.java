package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class searches extends RecyclerView.Adapter<searches.MyViewHolder> {
    private final recyclerviewinterface recyclerviewinterface;
    Context context;
    ArrayList<search> searchs;

    public searches(Context context, ArrayList<search> searchs, searchFragment recyclerviewinterface) {
        this.context = context;
        this.searchs = searchs;
        this.recyclerviewinterface = recyclerviewinterface;
    }




    //    public void searchadapter(Context context, ArrayList<search> searchs, recyclerviewinterface recyclerviewinterface1){
//        this.context = context;
//        this.searchs = searchs;
//        this.recyclerviewinterface = recyclerviewinterface1;
//    }
    @NonNull
    @Override
    public searches.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerviewrow, parent, false);
        return new searches.MyViewHolder(view, recyclerviewinterface);
    }

    @Override
    public void onBindViewHolder(@NonNull searches.MyViewHolder holder, int position) {
        holder.head.setText((searchs.get(position).getHead()));
        holder.body.setText((searchs.get(position).getBody()));
        holder.price.setText((searchs.get(position).getPrice()));
//        SearchItem searchItem = searchItems.get(position);
//        holder.textViewCombinedInfo.setText(searchItem.getCombinedInfo());
    }

    @Override
    public int getItemCount() {
        return searchs.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView head, body, price;
        public MyViewHolder(@NonNull View itemView, recyclerviewinterface recyclerviewinterface) {
            super(itemView);

//            imageView = itemView.findViewById(R.id.imageView7);
            head = itemView.findViewById(R.id.head);
            body = itemView.findViewById(R.id.body);
            price = itemView.findViewById(R.id.price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerviewinterface != null) {
                        int pos = getAbsoluteAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerviewinterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
