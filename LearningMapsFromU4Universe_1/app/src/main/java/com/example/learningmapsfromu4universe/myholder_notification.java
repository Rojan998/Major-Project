package com.example.learningmapsfromu4universe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myholder_notification extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView msg,msgDescription;
    public myholder_notification(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image_cardview);
        this.msg= itemView.findViewById(R.id.msg);
        this.msgDescription = itemView.findViewById(R.id.msgDescription);

    }
}
