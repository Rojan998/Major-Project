package com.example.learningmapsfromu4universe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_notification extends RecyclerView.Adapter<myholder_notification> {
    Context c;
    ArrayList<model_notification> models;

    public adapter_notification(Context c, ArrayList<model_notification> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public myholder_notification onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_for_notification,null);



        return new myholder_notification(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder_notification holder, int i) {
        holder.msg.setText(models.get(i).getMessage());
        holder.msgDescription.setText(models.get(i).getMessageDescription());
        holder.mImageView.setImageResource(models.get(i).getImg());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
