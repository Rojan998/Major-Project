package com.example.learningmapsfromu4universe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myholder_acknowledgement extends RecyclerView.ViewHolder {
    ImageView mImageView_ack;
    TextView msg_ack,msgDescription_ack;
    public myholder_acknowledgement(@NonNull View itemView) {
        super(itemView);

        this.mImageView_ack = itemView.findViewById(R.id.image_ack_cardview);
        this.msg_ack= itemView.findViewById(R.id.msg_ack);
        this.msgDescription_ack = itemView.findViewById(R.id.msgDescription_ack);
    }
}
