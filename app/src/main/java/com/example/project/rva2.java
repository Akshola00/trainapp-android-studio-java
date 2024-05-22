package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rva2 extends RecyclerView.Adapter<rva2.MyViewHolder> {
    Context context;

    public rva2(Context context, ArrayList<tm> tmss) {
        this.context = context;
        this.tmss = tmss;
    }

    ArrayList<tm> tmss;


    @NonNull
    @Override
    public rva2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerviewticketfrag, parent, false);
        return new rva2.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rva2.MyViewHolder holder, int position) {
        holder.alltext.setText(tmss.get(position).getSingleticket());
    }

    @Override
    public int getItemCount() {
        return tmss.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView alltext;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            alltext = itemView.findViewById(R.id.alltext);
        }
    }
}
