package com.example.eat2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class tacoBell extends AppCompatActivity {
    Toolbar tcobar;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taco_bell);


        firebaseAuth = FirebaseAuth.getInstance();
        tcobar = (Toolbar) findViewById(R.id.tBellBar);
        setSupportActionBar(tcobar);
        getSupportActionBar().setTitle("EAT IT");


    }

    private void llogout() {

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(tacoBell.this, registerOrLogin.class));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu: {
                llogout();
            }
        }
        return super.onOptionsItemSelected(item);
    }


}