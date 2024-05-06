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
        holder.tvfrom.setText((searchs.get(position).getFrom()));
        holder.tvto.setText((searchs.get(position).getTo()));
        holder.tvtimefrom.setText((searchs.get(position).getTimefrom()));
        holder.tvtmieto.setText((searchs.get(position).getTimeto()));
    }

    @Override
    public int getItemCount() {
        return searchs.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvfrom, tvto, tvtimefrom, tvtmieto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView7);
            tvfrom = itemView.findViewById(R.id.textView12);
            tvto = itemView.findViewById(R.id.textView14);
            tvtimefrom = itemView.findViewById(R.id.textView15);
            tvtmieto = itemView.findViewById(R.id.textView16);

        }
    }
}
