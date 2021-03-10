package com.example.solvefoundation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetails extends AppCompatActivity {
    TextView name,email,company,phone,website;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails);

        name=findViewById(R.id.name);
        email=findViewById(R.id.Email);
        company=findViewById(R.id.company);
        phone=findViewById(R.id.phone);
        website=findViewById(R.id.web);

        Intent intent = getIntent();
        if (intent != null) {
            String name1= intent.getStringExtra("name");
            name.setText(name1);

            String Email = intent.getStringExtra("Email");
            email.setText(Email);

            String company1 = intent.getStringExtra("Company");
            company.setText(company1);

            String phone1 = intent.getStringExtra("phone");
            phone.setText(phone1);

            String website1 = intent.getStringExtra("website");
            website.setText(website1);



            //   String api = intent.getStringExtra("api");
            //....
        }



    }
}
