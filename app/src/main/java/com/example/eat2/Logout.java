package com.example.eat2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    Button logoutbtn;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        firebaseAuth=FirebaseAuth.getInstance();
        toolbar=(Toolbar)findViewById(R.id.toolbarlogout);
        setSupportActionBar(toolbar);
        logoutbtn=(Button)findViewById(R.id.btnLogOut);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              logout();

            }
        });


    }
private void logout(){

    firebaseAuth.signOut();
    finish();
    startActivity(new Intent(this,registerOrLogin.class));




}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutMenu:{
            logout();}
        }
        return super.onOptionsItemSelected(item);
    }
}
