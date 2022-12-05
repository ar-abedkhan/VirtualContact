package com.abedkhan.virtualcontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.abedkhan.virtualcontact.databinding.ActivityLogeinBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Logein extends AppCompatActivity {
ActivityLogeinBinding binding;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLogeinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.registrationbtn.setOnClickListener(view -> {
            startActivity(new Intent(this,Registration.class));
        });


        firebaseAuth =FirebaseAuth.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        binding.logeinbtn.setOnClickListener(view -> {


            String mail=binding.inputmail.getText().toString().trim();
            String pass=binding.inputpass.getText().toString().trim();

            if (mail.equals("")){
                binding.inputmail.setError("User Mail required");
            }else if (pass.equals("")){
                binding.inputpass.setError("User Mail required");

            }else {
                logeinUser(mail,pass);
            }


        });


    }

    private void logeinUser(String mail, String pass) {
        Log.i("tag", "logeinUser: ");

        binding.logeinbtn.setVisibility(View.GONE);
        binding.registrationbtn.setVisibility(View.GONE);
        binding.logeinProgressbar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnSuccessListener(authResult -> {

            startActivity(new Intent(this,MainActivity.class));
            finish();

            Toast.makeText(this, "Successfully LogeIn", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {

            showAleart(e.getLocalizedMessage());

            binding.logeinbtn.setVisibility(View.GONE);
            binding.registrationbtn.setVisibility(View.GONE);
            binding.logeinProgressbar.setVisibility(View.VISIBLE);
        });

    }

    private void showAleart(String errMsg) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_baseline_error_24);
        builder.setTitle("Error");
        builder.setMessage(errMsg);

        builder.setPositiveButton("OK", (dialogInterface, i) -> {

        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();    }
}