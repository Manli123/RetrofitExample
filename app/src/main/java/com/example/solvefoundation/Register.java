package com.example.solvefoundation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Button register,AlreadyRegister;
    EditText name,email,password;
    public static final String TAG = "TAG";

    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);




        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.Pass);
        register=findViewById(R.id.register);
        AlreadyRegister=findViewById(R.id.AlreadyRegister);


        AlreadyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });


        fAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(v -> {
            String username=name.getText().toString().trim();
            String Email=email.getText().toString().trim();
            String pass=password.getText().toString().trim();


            if(TextUtils.isEmpty(username)){
                name.setError("username is required");
                return;
            }

            if(TextUtils.isEmpty(Email)){
                email.setError("username is required");
                return;
            }

            if(TextUtils.isEmpty(pass)){
                password.setError("username is required");
                return;
            }

            if (pass.length()<6){
                password.setError("It must be equal to 10 digits");
            }



            //register firebase
            fAuth.createUserWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        // send verification link




                        FirebaseUser fuser = fAuth.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Userregister user=new  Userregister(Email,pass);

                                Toast.makeText(Register.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();



                                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(Register.this,"data has been added sucessfully to firebase",Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(Register.this,"Failed,try again",Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                            }

                        });
                    }else {
                        Toast.makeText(Register.this,"try again",Toast.LENGTH_LONG).show();

                    }
                }
            });
        });


    }
}



