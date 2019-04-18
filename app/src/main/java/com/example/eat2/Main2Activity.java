package com.example.eat2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    TextView textView1;
    private TextView nameView;
    private TextView passwordView;
    private TextView phoneView;
    private TextView emailView;
    private Button signUP;
    private Button cancel;
    private ProgressDialog progressDialog;

    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    public void signFunction(View view){
        EditText nameView=findViewById(R.id.nameView);
        EditText passwordView=findViewById(R.id.passwordView);
        EditText phoneView=findViewById(R.id.phoneView);
        EditText emailView=findViewById(R.id.emailView);
        Log.i("name",nameView.getText().toString());
        Log.i("name",passwordView.getText().toString());
        Log.i("name",phoneView.getText().toString());
        Log.i("name",emailView.getText().toString());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebaseAuth =FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("costumer");
        textView1=findViewById(R.id.txtView1);
        nameView=findViewById(R.id.nameView);
        passwordView=findViewById(R.id.passwordView);
        phoneView=findViewById(R.id.phoneView);
        emailView=findViewById(R.id.emailView);
        cancel=findViewById(R.id.cancel);
        textView1.animate().translationYBy(-700).setDuration(2000);
        nameView.animate().alpha(1).setDuration(4000);
        passwordView.animate().alpha(1).setDuration(4000);
        phoneView.animate().alpha(1).setDuration(4000);
        emailView.animate().alpha(1).setDuration(4000);
        signUP=findViewById(R.id.signUp);
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addCostumer();
                progressDialog=new ProgressDialog(Main2Activity.this);
                progressDialog.setMessage("In Progress");
                progressDialog.show();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openregisterOrLogin();
            }
        });


    }
    public void  openregisterOrLogin(){
        Intent intent= new Intent(this,registerOrLogin.class);
        startActivity(intent);
    }


}

