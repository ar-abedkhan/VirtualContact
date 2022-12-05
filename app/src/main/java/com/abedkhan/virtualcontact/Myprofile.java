package com.abedkhan.virtualcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.abedkhan.virtualcontact.databinding.ActivityMyprofileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Myprofile extends AppCompatActivity {
ActivityMyprofileBinding binding;
FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMyprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
firebaseAuth=FirebaseAuth.getInstance();


       binding.logeout.setOnClickListener(view -> {
           firebaseAuth.signOut();
           startActivity(new Intent(this, Logein.class));
           finish();
       });








    }
}