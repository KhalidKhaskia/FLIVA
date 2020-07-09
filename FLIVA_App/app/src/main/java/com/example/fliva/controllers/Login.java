package com.example.fliva.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fliva.models.User;
import com.example.fliva.ui.MainActivity;
import com.example.fliva.R;

import com.example.fliva.ui.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    public static String TMPLCS;
    EditText SI_license,SI_password;
    Button btn_sginIn;
    TextView tv_SignUp;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SI_license = findViewById(R.id.txt_license);
        SI_password = findViewById(R.id.txt_password);
        btn_sginIn = findViewById(R.id.btn_signin);
        tv_SignUp = findViewById(R.id.txt_signup);
        users = FirebaseDatabase.getInstance().getReference("Users");

        tv_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });

        btn_sginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email = SI_email.getText().toString().trim();
                String license = SI_license.getText().toString().trim();
                String password = SI_password.getText().toString().trim();

                if(TextUtils.isEmpty(license))
                {
                    SI_license.setError("License is Required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    SI_password.setError("Password is Required");
                    return;
                }

                signIn(license,password);
            }
        });
    }
    private void signIn(final String license, final String password)
    {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.child(license).exists())
                {
                    if(!license.isEmpty())
                    {
                        User loginUser = snapshot.child(license).getValue(User.class);
                        if(loginUser.getPassword().equals(password))
                        {
                            Toast.makeText(Login.this,"Success Login",Toast.LENGTH_SHORT).show();
                            Intent Regsterintent =  new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(Regsterintent);
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Login.this," check it ",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    SI_license.setError("License Number Is Not Regesterd");
                    //Toast.makeText(Login.this,"License Does Not Reg",Toast.LENGTH_SHORT).show();
                }
                TMPLCS=license;
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("License",license);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(Login.this,"Failed to read value.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
