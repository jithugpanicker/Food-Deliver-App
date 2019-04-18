package com.example.eat2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class cardView extends AppCompatActivity {
    private Toolbar toolbar;

    private ListView listView;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        firebaseAuth = FirebaseAuth.getInstance();
        setUpView();
        initToolbar();
        setUpListView();


    }

    private void setUpView() {
        toolbar = (Toolbar) findViewById(R.id.ToolbarMain);
        listView = (ListView) findViewById(R.id.lvMain);


    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EAT IT");


    }

    private void setUpListView() {
        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    startActivity(new Intent(cardView.this, dominoes.class));
                }
                if (position == 1) {
                    startActivity(new Intent(cardView.this, kfc.class));
                }
                if (position == 2) {
                    startActivity(new Intent(cardView.this, subway.class));
                }
                if (position == 3) {
                    startActivity(new Intent(cardView.this, tacoBell.class));
                }
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] title, String[] description) {
            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item, null);

            }
            title = (TextView) convertView.findViewById(R.id.tvMain);
            description = (TextView) convertView.findViewById(R.id.tvDescription);
            imageView = (ImageView) convertView.findViewById(R.id.ivMain);
            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);


            if (titleArray[position].equalsIgnoreCase("Dominoes")) {
                imageView.setImageResource(R.drawable.dpizza);

            } else if (titleArray[position].equalsIgnoreCase("Kfc")) {
                imageView.setImageResource(R.drawable.kfc);

            } else if (titleArray[position].equalsIgnoreCase("Subway")) {
                imageView.setImageResource(R.drawable.subway);

            } else {
                imageView.setImageResource(R.drawable.tbells);

            }
            return convertView;


        }
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        Intent intent = new Intent(cardView.this, MainActivity.class);
        startActivity(intent);
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
                Logout();
                break;
            }
            case R.id.profileMenu: {
                startActivity(new Intent(cardView.this, ProfileActivity.class));
                break;
            }



        }

        return super.onOptionsItemSelected(item);
    }
}