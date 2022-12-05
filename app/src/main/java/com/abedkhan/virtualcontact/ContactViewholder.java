package com.abedkhan.virtualcontact;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactViewholder extends RecyclerView.ViewHolder {
    CircleImageView profileimg;
    ImageView message,call,mail;
    TextView userName,userNumber,userRelation,userLocation;


    public ContactViewholder(@NonNull View itemView) {
        super(itemView);

    profileimg=itemView.findViewById(R.id.profile);
    message=itemView.findViewById(R.id.message);
    call=itemView.findViewById(R.id.call);
    mail=itemView.findViewById(R.id.mail);
    userNumber=itemView.findViewById(R.id.userNumber);
    userName=itemView.findViewById(R.id.username);
    userRelation=itemView.findViewById(R.id.userRelation);
    userLocation=itemView.findViewById(R.id.userLocation);










    }
}
