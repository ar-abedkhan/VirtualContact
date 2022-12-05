package com.abedkhan.virtualcontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abedkhan.virtualcontact.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
List<ContactModel>contactModelList;
DatabaseReference databaseReference;
String myid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contactModelList=new ArrayList<>();


binding.addbtn.setOnClickListener(view -> {
    startActivity(new Intent(this,AddContact.class));
});


binding.myProfile.setOnClickListener(view -> {
    startActivity(new Intent(this,Myprofile.class));
});

        firebaseAuth =FirebaseAuth.getInstance();

//
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        myid=firebaseUser.getUid();
        if (myid!=null){

        }


        databaseReference= FirebaseDatabase.getInstance().getReference("user");

databaseReference.child(myid).child("Contact").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        contactModelList.clear();
        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
            ContactModel contactModel=dataSnapshot.getValue(ContactModel.class);

            if (contactModel!=null) {
                contactModelList.add(contactModel);
            }
        }

        ContactAdapter contactAdapter=new ContactAdapter(contactModelList,MainActivity.this);
        binding.contactrecycler.setAdapter(contactAdapter);


    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});



    }
}