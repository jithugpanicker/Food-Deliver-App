package com.example.eat2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class subway extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway);
        ListView slist=findViewById(R.id.subwayList);
        ArrayList<String> sProduct=new ArrayList<String>();
        sProduct.add("dominoes");
        sProduct.add("dominoes");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sProduct);
        slist.setAdapter(arrayAdapter);

    }
}
