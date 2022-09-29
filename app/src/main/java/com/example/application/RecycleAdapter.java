package com.example.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{

    private final ArrayList<ModelClass.data> filteredUserList;

    public RecycleAdapter(Context context, ArrayList<ModelClass.data> list){
        filteredUserList=list;
    }


    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.backgroundlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyViewHolder holder, int position) {
        holder.title.setText(filteredUserList.get(position).getName());
        holder.description.setText("Created At \n".concat(filteredUserList.get(position).getCreated_at()));
        holder.publish.setText("Updated At \n".concat(filteredUserList.get(position).getUpdated_at()));

        Picasso.get()
                .load(filteredUserList.get(position).getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return filteredUserList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView publish;
        private ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titlee);
            description = (TextView) view.findViewById(R.id.created);
            publish = (TextView) view.findViewById(R.id.updated);
            imageView=view.findViewById(R.id.photo);
        }
    }
}
