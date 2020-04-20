package com.example.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText emailid,password,password2,fname,lname,phn;
    Button signup,back;
    //TextView tvsignup;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirebaseAuth =FirebaseAuth.getInstance();
        fname= findViewById(R.id.editText3);
        lname=findViewById(R.id.editText4);
        emailid=findViewById(R.id.editText5);
        password=findViewById(R.id.editText6);
        phn=findViewById(R.id.editText7);
        password2=findViewById(R.id.editText8);
        signup=findViewById(R.id.button4);
        back=findViewById(R.id.button3);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailid.getText().toString();
                String pwd = password.getText().toString();
                String fn=fname.getText().toString();
                String ln=lname.getText().toString();
                String ph=phn.getText().toString();
                String pwd2=password2.getText().toString();
                if (email.isEmpty()) {
                    emailid.setError("Please enter email id");
                    emailid.requestFocus();
                }
                if (pwd.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                if (fn.isEmpty()) {
                    fname.setError("Please enter first name");
                    fname.requestFocus();
                }
                if (ln.isEmpty()) {
                    lname.setError("Please enter last name");
                    lname.requestFocus();
                }
                if (pwd2.isEmpty()) {
                    password2.setError("Please enter the password again");
                    password2.requestFocus();
                }
                if (ph.isEmpty()) {
                    phn.setError("Please enter your phone number");
                    phn.requestFocus();
                }
                if (!pwd.equals(pwd2)){
                    password2.setError("Passwords are not same.Please check again");
                    password2.requestFocus();
                }
                if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!email.isEmpty() && !pwd.isEmpty()) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "SignUp unsuccessful,Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(RegisterActivity.this, home.class));
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                }
            }

        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }
}
