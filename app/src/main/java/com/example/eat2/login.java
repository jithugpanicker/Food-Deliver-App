package com.example.eat2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    TextView textView1;
    // private TextView nameView;
    private TextView passwordView;
    //private TextView phoneView;
    private TextView emailView;
    TextView forgotPassword;
    private Button signIn;
    private Button cancel;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    public void signFunction(View view) {
        //EditText nameView = findViewById(R.id.nameView);
        EditText passwordView = findViewById(R.id.passwordView);
        //  EditText phoneView = findViewById(R.id.phoneView);
        EditText emailView = findViewById(R.id.emailView);
        //Log.i("name", nameView.getText().toString());
        Log.i("name", passwordView.getText().toString());
        // Log.i("name", phoneView.getText().toString());
        Log.i("name", emailView.getText().toString());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        textView1 = findViewById(R.id.txtView1);
        // nameView = findViewById(R.id.nameView);
        passwordView = findViewById(R.id.passwordView);
        //phoneView = findViewById(R.id.phoneView);
        emailView = findViewById(R.id.emailView);
        cancel = findViewById(R.id.cancel);
        signIn = findViewById(R.id.signIn);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        textView1.animate().translationYBy(-700).setDuration(2000);
        // nameView.animate().alpha(1).setDuration(4000);
        passwordView.animate().alpha(1).setDuration(4000);
        // phoneView.animate().alpha(1).setDuration(4000);
        emailView.animate().alpha(1).setDuration(4000);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openregisterOrLogin();
            }
        });
        forgotPassword=(TextView)findViewById(R.id.fgtpass);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,PasswordActivity.class));

            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(emailView.getText().toString(),passwordView.getText().toString());
            }
        });
    }

    public void openregisterOrLogin() {
        Intent intent = new Intent(this, registerOrLogin.class);
        startActivity(intent);
    }


    public void validate(String costumerName, String costumerPassword){
        progressDialog.setMessage("In Progress");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(costumerName,costumerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    checkEmailVerification();
                }
                else
                    Toast.makeText(login.this, "failed login", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });
    }
    private void checkEmailVerification()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        Boolean emailflag=firebaseUser.isEmailVerified();
        if(emailflag){
            finish();
            startActivity(new Intent(this,cardView.class));
        }
        else{
            Toast.makeText(this, "verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
