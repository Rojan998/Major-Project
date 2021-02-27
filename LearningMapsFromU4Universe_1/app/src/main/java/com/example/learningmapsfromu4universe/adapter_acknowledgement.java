package com.example.learningmapsfromu4universe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_acknowledgement extends RecyclerView.Adapter<myholder_acknowledgement> {
    Context c;
    ArrayList<model_acknowledgement> models;

    public adapter_acknowledgement(Context c, ArrayList<model_acknowledgement> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public myholder_acknowledgement onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_for_acknowledgement,null);



        return new myholder_acknowledgement(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder_acknowledgement holder_ack, int i) {
        holder_ack.msg_ack.setText(models.get(i).getMessage_ack());
        holder_ack.msgDescription_ack.setText(models.get(i).getMessageDescription_ack());
        holder_ack.mImageView_ack.setImageResource(models.get(i).getImg_ack());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
