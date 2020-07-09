package com.example.fliva.ui;
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
import com.example.fliva.R;
import com.example.fliva.controllers.Login;
import com.example.fliva.controllers.Registration;
import com.example.fliva.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity
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
		setContentView(R.layout.activity_profile);
		TAG="Registration";
		User tmpusr ;
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
		btn_SignUp = findViewById(R.id.btn_Update);
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

				if(license.toString().isEmpty())
				{
					D_license.setError("Please enter value");
					return;
				}
				if(license.toString().length()<6 || license.toString().length()>8)
				{
					D_license.setError("License number must be between 6 to 8 digits");
					return;
				}
				if(firstname.toString().isEmpty())
				{
					D_Fname.setError("Please enter value");
					return;
				}
				if(lastname.toString().isEmpty())
				{
					D_Lname.setError("Please enter value");
					return;
				}
				if(id.toString().isEmpty())
				{
					D_id.setError("Please enter value");
					return;
				}
				if(phonenumber.toString().isEmpty())
				{
					D_phone.setError("Please enter value");return;
				}
				if(email.toString().isEmpty())
				{
					D_email.setError("Please enter value");return;
				}
				if(password.toString().isEmpty())
				{
					D_password.setError("Please enter value");return;
				}


				final User user = new User(firstname,lastname,id,phonenumber,license,email,password);

				users.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull DataSnapshot snapshot)
					{
						{
							users.child(license).child(user.getLicense()).setValue(user);
							Toast.makeText(Profile.this,"Updated Succesfully !",Toast.LENGTH_SHORT).show();
							//startActivity( new Intent(getApplicationContext(),Login.class));
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
		Intent intent = getIntent();
		final String tmplicense = Login.TMPLCS.toString();;
		users.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot)
			{

				Log.d("TMPLCS",tmplicense.toString());
				if(snapshot.child(tmplicense).exists())
				{
					if(!tmplicense.isEmpty())
					{
						User loginUser = snapshot.child(tmplicense).getValue(User.class);
						D_email.setText(loginUser.getEmail());
						D_Fname.setText(loginUser.getFirstname());
						D_id.setText(loginUser.getId());
						D_license.setText(loginUser.getLicense());
						D_Lname.setText(loginUser.getLicense());
						D_password.setText(loginUser.getPassword());
						D_phone.setText(loginUser.getPhone());

					}
					else
					{
						Toast.makeText(Profile.this," check it ",Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					D_license.setError("License Number Is Not Regesterd");
					//Toast.makeText(Login.this,"License Does Not Reg",Toast.LENGTH_SHORT).show();
				}
			}
			@Override
			public void onCancelled(@NonNull DatabaseError error)
			{
				Toast.makeText(Profile.this,"Failed to read value.",Toast.LENGTH_SHORT).show();
			}
		});


	}
}