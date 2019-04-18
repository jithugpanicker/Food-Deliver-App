package com.example.eat2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        firebaseAuth=FirebaseAuth.getInstance();
        passwordEmail=(EditText)findViewById(R.id.sentReset);
        resetPassword=(Button)findViewById(R.id.btnPassReset);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=passwordEmail.getText().toString().trim();
                if(userEmail.equals("")){
                    Toast.makeText(PasswordActivity.this, "Enter Your Registered Email", Toast.LENGTH_SHORT).show();

                }else{
                    firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PasswordActivity.this, "Password Reset Email Sent ", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this, registerOrLogin.class));
                            } else {
                                Toast.makeText(PasswordActivity.this, "Error Sending Password ", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }

            }
        });
    }
}
