package com.example.eat2;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView profileName,profileEmail,profilePhone;
    private Button update;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName=findViewById(R.id.reName);
        profileEmail=findViewById(R.id.reEmail);
        profilePhone=findViewById(R.id.rePhone);
        update=findViewById(R.id.updateBtn);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        progressDialog=new ProgressDialog(ProfileActivity.this);
        progressDialog.setMessage("In Progress");
        progressDialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                progressDialog.dismiss();
                Names names2=dataSnapshot.getValue(Names.class);
                profileName.setText("Name:"+names2.getName());
                profilePhone.setText("Phone Number:"+names2.getPhoneNumber());
                profileEmail.setText("Email:"+names2.getEmailId());

                

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast.makeText(ProfileActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
