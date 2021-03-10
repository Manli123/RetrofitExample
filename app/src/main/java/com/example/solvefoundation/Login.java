package com.example.solvefoundation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText user,password;
    Button logg;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);



        firebaseAuth = FirebaseAuth.getInstance();

        user = findViewById(R.id.uemail);
        password = findViewById(R.id.upass);
        logg = findViewById(R.id.login);


        logg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=user.getText().toString().trim();
                String Password=password.getText().toString().trim();

                if (Email.isEmpty()){
                    user.setError("Email is required");
                    user.requestFocus();
                    return;
                }
                /*if (Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    user.setError("please enter a valid email");
                    user.requestFocus();
                    return;
                }*/

                if (Password.isEmpty()){
                    password.setError("Email is required");
                    password.requestFocus();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //redirect to another activity

                            Toast.makeText(Login.this,"wow",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(Login.this,MainActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(Login.this,"failed to log in,please che credentials",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        });
    }
}


