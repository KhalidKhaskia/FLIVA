package com.example.fliva.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fliva.models.User;
import com.example.fliva.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity
{
    EditText D_Fname,D_Lname,D_id,D_license,D_phone,D_email,D_password;
    Button btn_SignUp;
    TextView tv_SignIn;
    ProgressBar progressBar;
    private DatabaseReference users;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        TAG="Registration";

        // fire base
        users = FirebaseDatabase.getInstance().getReference("Users");

        //labels
        D_Fname = findViewById(R.id.txt_reg_firstName);
        D_Lname = findViewById(R.id.txt_reg_lastName);
        D_id = findViewById(R.id.txt_reg_id);
        D_phone = findViewById(R.id.txt_reg_phone);
        D_license = findViewById(R.id.txt_reg_license);
        D_email = findViewById(R.id.txt_reg_email);
        D_password = findViewById(R.id.txt_reg_password);

       //buttons
        btn_SignUp = findViewById(R.id.btn_signup);
        tv_SignIn = findViewById(R.id.txt_signin);

        tv_SignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity( new Intent(getApplicationContext(),Login.class));
            }
        });

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String firstname=D_Fname.getText().toString();
                String lastname=D_Lname.getText().toString();
                String id=D_id.getText().toString();
                String phonenumber=D_phone.getText().toString();
                final String license=D_license.getText().toString();
                String email=D_email.getText().toString();
                String password=D_password.getText().toString();

                if(license.toString().length()<6 || license.toString().length()>8)
                {
                    D_license.setError("License number must be between 6 to 8 digits");
                }

                final User user = new User(firstname,lastname,id,phonenumber,license,email,password);

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        if(snapshot.child(user.getLicense()).exists())
                        {
                            Toast.makeText(Registration.this,"This licence number is Already Exist!",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            users.child(user.getLicense()).setValue(user);
                            Toast.makeText(Registration.this,"Success Register!",Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(getApplicationContext(),Login.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });

    }
}
