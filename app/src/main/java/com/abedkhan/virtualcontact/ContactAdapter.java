package com.abedkhan.virtualcontact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewholder> {
    List<ContactModel>contactModelList;
    Context context;

    public ContactAdapter(List<ContactModel> contactModelList, Context context) {
        this.contactModelList = contactModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.contactrrcycler,parent,false);

        return new ContactViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewholder holder, int position) {

        ContactModel contactModel=contactModelList.get(position);


        holder.userName.setText(contactModel.getUser_name());
        holder.userNumber.setText(contactModel.getUser_number());
        holder.userRelation.setText(contactModel.getUser_relation());
        holder.userLocation.setText(contactModel.getUser_location());

        String phone=contactModel.user_number;
        String sms=contactModel.user_message;

        Glide.with(context).load(contactModel.getUser_img()).placeholder(R.drawable.ic_baseline_person_24).into(holder.profileimg);
        Glide.with(context).load(contactModel.getUser_number()).placeholder(R.drawable.call).into(holder.call);
        Glide.with(context).load(contactModel.getUser_mail()).placeholder(R.drawable.gmail).into(holder.mail);
        Glide.with(context).load(contactModel.getUser_message()).placeholder(R.drawable.message).into(holder.message);


holder.call.setOnClickListener(view -> {
    Intent intent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone,null));
     context.startActivity(intent);
});


holder.message.setOnClickListener(view -> {
    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",phone,null));
    intent.putExtra("sms_body",sms);
     context.startActivity(intent);
});






//        String phoneNo = "";//The phone number you want to text
//        String sms= "";//The message you want to text to the phone
//
//        Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneNo, null));
//        smsIntent.putExtra("sms_body",sms);
//        startActivity(smsIntent);
//

    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }
}
