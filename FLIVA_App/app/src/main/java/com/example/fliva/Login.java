package com.example.fliva;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText SI_license,SI_email,SI_password; // SI = Sgin IN
    Button btn_sginIn;
    TextView tv_SignUp;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SI_license = findViewById(R.id.txt_license);
        SI_email = findViewById(R.id.txt_email);
        SI_password = findViewById(R.id.txt_password);
        btn_sginIn = findViewById(R.id.btn_signin);
        tv_SignUp = findViewById(R.id.txt_signup);
        fAuth = FirebaseAuth.getInstance();

        tv_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });

        btn_sginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = SI_email.getText().toString().trim();
                String password = SI_password.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    SI_email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    SI_password.setError("Password is Required");
                    return;
                }
                if(password.length() < 6)
                {
                    SI_password.setError("Password must be more than 6 characters");
                }

                // authinticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this,"Error ! "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
