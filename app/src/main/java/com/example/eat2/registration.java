package com.example.eat2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {
     TextView textView;
   private EditText userName,userPassword,userEmail,userPhone;
   private Button signUp,cancel;
    private ProgressDialog progressDialog;
    String email,name,phone,password;

    private FirebaseAuth firebaseAuth;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        auth=FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

    textView=findViewById(R.id.textView);

        userName=findViewById(R.id.rNameView);
        userPassword=findViewById(R.id.rPasswordView);
        userEmail=findViewById(R.id.rEmailView);
        userPhone =findViewById(R.id.rPhoneView);
        signUp=findViewById(R.id.signUp);
        textView.animate().translationYBy(-500).setDuration(2000);
        userPassword.animate().alpha(1).setDuration(4000);
        userName.animate().alpha(1).setDuration(4000);

        userPhone.animate().alpha(1).setDuration(4000);
        userEmail.animate().alpha(1).setDuration(4000);





        signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     if(userName.length()==0)
                    {
                        userName.setError("Empty Field");

                    }
                    else if(userPassword.length()==0)
                    {
                        userPassword.setError("Empty Field");
                    }
                    else if(userPassword.length()<6){
                        userPassword.setError("Minimum 6 Characters Required");
                     }
                    else if(userPhone.length()==0)
                    {
                        userPhone.setError("Empty Field");
                    }
                     else if(userPhone.length()<10)
                     {
                         userPhone.setError("Less than 10 digits");
                     }
                    else if(userPhone.length()>10)
                    {
                        userPhone.setError("More than 10 digits");
                    }

                    else if(userEmail.length()==0)
                    {
                        userEmail.setError("Empty Field");
                    }
                    else{
                    if (validate()) {
                        progressDialog=new ProgressDialog(registration.this);
                        progressDialog.setMessage("In Progress");
                        progressDialog.show();
                        String user_email = userEmail.getText().toString().trim();
                        String user_password = userPassword.getText().toString().trim();
                        firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                        sendEmailVerification();

                                } else {
                                    checkEmail(userEmail);
                                    progressDialog.dismiss();
                                    Toast.makeText(registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });


                    }}

                }
            });
        cancel=(Button)findViewById(R.id.rcancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registration.this,registerOrLogin.class));
            }
        });

        }


    private Boolean validate()
    {
        Boolean result= false;
         name=userName.getText().toString();
          password=userPassword.getText().toString();
        email=userEmail.getText().toString();
         phone=userPhone.getText().toString();
        if(name.isEmpty()||password.isEmpty()||email.isEmpty()||phone.isEmpty()){
            Toast.makeText(this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result =true;

        }
        return result;
    }
    public  void  checkEmail(View v)
    {
        auth.fetchProvidersForEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                boolean check=!task.getResult().getProviders().isEmpty();
                if(!check)
                {
                    Toast.makeText(registration.this, "On going", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(registration.this, "Email already Present", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
private void sendEmailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(registration.this, "Successfully Registered,Email Verification Sent", Toast.LENGTH_SHORT).show();

                      firebaseAuth.signOut();
                      finish();
                      startActivity(new Intent(registration.this,registerOrLogin.class));
                      
                    }
                    else{
                        Toast.makeText(registration.this, "Verification Email Not Sent", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
}
private void sendUserData(){
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference myref=firebaseDatabase.getReference(firebaseAuth.getUid());
    Names names=new Names(name,phone,email);
    myref.setValue(names);
}
}

