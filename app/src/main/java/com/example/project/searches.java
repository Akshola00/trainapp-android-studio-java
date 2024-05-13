package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class searches extends RecyclerView.Adapter<searches.MyViewHolder> {
    Context context;
    ArrayList<search> searchs;

    public searches(Context context, ArrayList<search> searchs) {
        this.context = context;
        this.searchs = searchs;
    }

    public void searchadapter(Context context, ArrayList<search> searchs){
        this.context = context;
        this.searchs = searchs;
    }
    @NonNull
    @Override
    public searches.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerviewrow, parent, false);
        return new searches.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searches.MyViewHolder holder, int position) {
        holder.head.setText((searchs.get(position).getHead()));
        holder.body.setText((searchs.get(position).getBody()));
        holder.price.setText((searchs.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return searchs.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView head, body, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            imageView = itemView.findViewById(R.id.imageView7);
            head = itemView.findViewById(R.id.head);
            body = itemView.findViewById(R.id.body);
            price = itemView.findViewById(R.id.price);


        }
    }
}
