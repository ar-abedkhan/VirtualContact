package com.abedkhan.virtualcontact;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abedkhan.virtualcontact.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
ActivityRegistrationBinding binding;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
DatabaseReference databaseReference;
String curreantUiD,location,relation,userId,profileImg,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistrationBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());


        binding.logeinBtn.setOnClickListener(view -> {
            startActivity(new Intent(this,Logein.class));
            finish();
        });


        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("user");



        binding.registrationBtn.setOnClickListener(view -> {


            String name=binding.userName.getText().toString().trim();
            String email=binding.userMail.getText().toString().trim();
            String number=binding.userNumber.getText().toString().trim();
            String pass=binding.userPassword.getText().toString().trim();

            if (name.equals("")){
                binding.userName.setError("User name required");
            }else if (email.equals("")){             //matches((^[a-zA-Z0-9._-]{3,}$)
                binding.userMail.setError("User Mail required");
            }else if (number.equals("")){
                binding.userNumber.setError("User Number required");
            }else if (pass.equals("")){
                binding.userPassword.setError("User Password required");
            }else {

                registerUser(name,email,number,pass);
            }





        });













    }

    private void registerUser(String name, String email, String number, String pass) {

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(authResult -> {

            firebaseUser =FirebaseAuth.getInstance().getCurrentUser();
            curreantUiD= firebaseUser.getUid();


            Map<String,Object> dataUpdate=new HashMap<>();
            dataUpdate.put("user_name",name);
            dataUpdate.put("user_mail",email);
            dataUpdate.put("user_pass",pass);
            dataUpdate.put("user_number",number);
            dataUpdate.put("user_img",profileImg);
            dataUpdate.put("user_location",location);
            dataUpdate.put("user_relation",relation);
            dataUpdate.put("user_message",message);
            dataUpdate.put("user_id",userId);

            databaseReference.child(curreantUiD).setValue(dataUpdate).addOnSuccessListener(unused -> {

                startActivity(new Intent(this,MainActivity.class));
                finish();
                Toast.makeText(this, "Registration Successful..", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(e -> {

                showAleart(e.getLocalizedMessage());

            });

            binding.registrationBtn.setVisibility(View.GONE);
            binding.logeinBtn.setVisibility(View.GONE);
            binding.registrationProgressbar.setVisibility(View.VISIBLE);

            startActivity(new Intent(this,MainActivity.class));
            finish();
            Toast.makeText(this, "Registration Successful..", Toast.LENGTH_SHORT).show();

        }).addOnFailureListener(e -> {

            showAleart(e.getLocalizedMessage());

            binding.registrationBtn.setVisibility(View.VISIBLE);
            binding.logeinBtn.setVisibility(View.VISIBLE);
            binding.registrationProgressbar.setVisibility(View.GONE);

        });

    }

    private void showAleart(String errorMSG) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.ic_baseline_error_24);
        builder.setTitle("Error");
        builder.setMessage(errorMSG);

        builder.setPositiveButton("OK", (dialogInterface, i) -> {

        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}