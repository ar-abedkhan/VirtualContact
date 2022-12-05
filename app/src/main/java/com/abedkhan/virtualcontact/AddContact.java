package com.abedkhan.virtualcontact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abedkhan.virtualcontact.databinding.ActivityAddContactBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddContact extends AppCompatActivity {
ActivityAddContactBinding binding;
DatabaseReference databaseReference;
FirebaseAuth firebaseAuth;
StorageReference storageReference;
String profileUrl="",contactId;
ProgressDialog dialog;
FirebaseUser firebaseUser;
Uri profileImgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        dialog=new ProgressDialog(this);
        dialog.setTitle("Contact Adding..");
        dialog.setMessage("Please Wait");


        databaseReference= FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth=FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("Contact_profile");



     binding.addContact.setOnClickListener(view -> {



         String name=binding.inputname.getText().toString().trim();
         String mail=binding.inputmail.getText().toString().trim();
         String number=binding.inputNumber.getText().toString().trim();
         String location=binding.location.getText().toString().trim();
         String relation=binding.relation.getText().toString().trim();

         firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
         String myId = firebaseUser.getUid();


         databaseReference = FirebaseDatabase.getInstance().getReference("user").child(myId);
         contactId = databaseReference.push().getKey();

         if (profileImgUri==null){


             if (name.equals("")){
                 binding.inputname.setError("Contact name required");
             }else if (number.equals("")){
                 binding.inputNumber.setError("Contact number required");
             }else if (location.equals("")){
                 binding.location.setError("Contact location required");
             }else if (relation.equals("")){
                 binding.relation.setError("Contact relation required");
             }else {

                 dialog.show();




                 HashMap<String , Object> dataUpdate =new HashMap<>();
                 dataUpdate.put("user_name",name);
                 dataUpdate.put("user_mail",mail);
                 dataUpdate.put("user_number",number);
                 dataUpdate.put("user_location",location);
                 dataUpdate.put("user_relation",relation);
                 dataUpdate.put("user_img",profileUrl);

                 databaseReference.child("Contact").child(contactId).setValue(dataUpdate).addOnCompleteListener(task1 -> {


                     if (task1.isSuccessful()) {

                         finish();

                         Log.i("tag", "null profile data update ");
                         Toast.makeText(this, "Contact Added Successfully", Toast.LENGTH_SHORT).show();

                         dialog.dismiss();

                     } else {
                         Toast.makeText(this, "Contact Adding failed", Toast.LENGTH_SHORT).show();
                     }
                 });

             }



         }else {


//             firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//             String myId = firebaseUser.getUid();
//
//
//             databaseReference = FirebaseDatabase.getInstance().getReference("user").child(myId);
//             contactId = databaseReference.push().getKey();
//         storageReference.child(name+System.currentTimeMillis());
             storageReference.child(contactId).putFile(profileImgUri).addOnCompleteListener(task -> {
                 if (task.isSuccessful()) {
                     storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                         profileUrl = String.valueOf(profileImgUri);


                         if (name.equals("")) {
                             binding.inputname.setError("Contact name required");
                         } else if (number.equals("")) {
                             binding.inputNumber.setError("Contact number required");
                         } else if (location.equals("")) {
                             binding.location.setError("Contact location required");
                         } else if (relation.equals("")) {
                             binding.relation.setError("Contact relation required");
                         } else {

                             HashMap<String, Object> dataUpdate = new HashMap<>();
                             dataUpdate.put("user_name", name);
                             dataUpdate.put("user_mail", mail);
                             dataUpdate.put("user_number", number);
                             dataUpdate.put("user_location", location);
                             dataUpdate.put("user_relation", relation);
                             dataUpdate.put("user_img", profileUrl);

                             databaseReference.child("Contact").child(contactId).setValue(dataUpdate).addOnCompleteListener(task1 -> {


                                 if (task1.isSuccessful()) {

                                     finish();

                                     Log.i("tag", "data update ");
                                     Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();

                                     dialog.dismiss();

                                 } else {
                                     Toast.makeText(this, "Contact Adding failed", Toast.LENGTH_SHORT).show();
                                 }
                             });


                         }


                     }).addOnFailureListener(e -> {

                     });
                 }
             });


         }



















//         if (name.equals("")){
//             binding.inputname.setError("Contact name required");
//         }else if (number.equals("")){
//             binding.inputNumber.setError("Contact number required");
//         }else if (location.equals("")){
//             binding.location.setError("Contact location required");
//         }else if (relation.equals("")){
//             binding.relation.setError("Contact relation required");
//         }else {
//
//             createContact(name,mail,location,relation,number);
//
//
//         }
     });





     binding.profileimg.setOnClickListener(view -> {
         Intent intent=new Intent();
         intent.setType("image/*");
         intent.setAction(Intent.ACTION_GET_CONTENT);
         startActivityForResult(intent,101);

     });



    }


//    private void createContact(String name, String mail, String location, String relation, String number) {
//
//        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
//        String myId=firebaseUser.getUid();
//
//        databaseReference= FirebaseDatabase.getInstance().getReference("user").child(myId);
//             contactId=databaseReference.push().getKey();
//             storageReference.putFile(profileImgUri).addOnCompleteListener(task -> {
//                 if (task.isSuccessful()){
//                     storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
//                         profileUrl=String.valueOf(profileImgUri);
//
//
//
//
//
//
//                     }).addOnFailureListener(e -> {
//
//                     });
//                 }
//             });

//        HashMap<String , Object> dataUpdate =new HashMap<>();
//        dataUpdate.put("user_name",name);
//        dataUpdate.put("user_mail",mail);
//        dataUpdate.put("user_number",number);
//        dataUpdate.put("user_location",location);
//        dataUpdate.put("user_relation",relation);
//
//        databaseReference.child("Contact").child(contactId).setValue(dataUpdate).addOnCompleteListener(task -> {
//
//            if (task.isSuccessful()) {
//
//                finish();
//
//                Log.i("tag", "data update ");
//                Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
//
//                dialog.dismiss();
//
//            } else {
//                Toast.makeText(this, "Contact Adding failed", Toast.LENGTH_SHORT).show();
//            }
//        });



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==101 && resultCode==RESULT_OK && data!=null){
            profileImgUri=data.getData();
            binding.profileimg.setImageURI(profileImgUri);
        }
    }
}