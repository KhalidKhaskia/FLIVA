package com.example.fliva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity
{
    EditText D_Fname,D_Lname,D_id,D_license,D_phone,D_email,D_password;
    Button btn_SignUp;
    TextView tv_SignIn;
    FirebaseAuth mFirebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        mFirebaseAuth = FirebaseAuth.getInstance();
        D_Fname = findViewById(R.id.txt_reg_firstName);
        D_Lname = findViewById(R.id.txt_reg_lastName);
        D_id = findViewById(R.id.txt_reg_id);
        D_license = findViewById(R.id.txt_reg_license);
        D_phone = findViewById(R.id.txt_reg_phone);
        D_email = findViewById(R.id.txt_reg_email);
        D_password = findViewById(R.id.txt_reg_password);
        btn_SignUp = findViewById(R.id.btn_signup);
        tv_SignIn = findViewById(R.id.txt_signin);

        if(mFirebaseAuth.getCurrentUser() != null)
        {
            startActivity( new Intent(getApplicationContext(),Login.class));
            finish();
        }

        tv_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),Login.class));
            }
        });

        btn_SignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = D_email.getText().toString().trim();
                String password = D_password.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    D_email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    D_password.setError("Password is Required");
                    return;
                }
                if(password.length() < 6)
                {
                    D_password.setError("Password must be more than 6 characters");
                }

                // fire base regesteration
                mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Registration.this,"User Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Registration.this,"Error ! "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}
