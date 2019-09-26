package com.example.scouts;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scouts.DBHelper;
import com.example.scouts.R;
import com.example.scouts.Register;
import com.example.scouts.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DataModel> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdaptor mAdapter;
    ImageView menu,logout;
    DataModel movie;
    LinearLayout gallery,shop,event,activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (ImageView) findViewById(R.id.contactForm);
        logout = (ImageView) findViewById(R.id.menuBtn);
        recyclerView = (RecyclerView) findViewById(R.id.eventRecycler);
        gallery= (LinearLayout) findViewById(R.id.gallery);
        shop= (LinearLayout) findViewById(R.id.shop);
        event= (LinearLayout) findViewById(R.id.events);
        activity= (LinearLayout) findViewById(R.id.activity);


        mAdapter = new RecyclerAdaptor(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
         movie = new DataModel("Scouts transforms recycled bottles", "World Scouts");
        mAdapter.addMessage(movie);
        movie = new DataModel("Scouts transforms recycled bottles", "World Scouts");
        mAdapter.addMessage(movie);
        movie = new DataModel("Scouts transforms recycled bottles", "World Scouts");
        mAdapter.addMessage(movie);
        movie = new DataModel("Scouts transforms recycled bottles", "World Scouts");
        mAdapter.addMessage(movie);
        movie = new DataModel("Scouts transforms recycled bottles", "World Scouts");
        mAdapter.addMessage(movie);
        movie = new DataModel("Scouts transforms recycled bottles", "World Scouts");
        mAdapter.addMessage(movie);
        movie = new DataModel("Scouts transforms recycled bottles", "World Scouts");
        mAdapter.addMessage(movie);


        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, galleryview.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Contact.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(MainActivity.this, userlogin.class));
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, Shop.class);
                startActivity(intent);
            }
        });




       /* activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, galleryview.class);
                startActivity(intent);
            }
        });*/

        prepareMovieData();
    }

    private void prepareMovieData() {


        DataModel movie = new DataModel("Scouts transforms recycled bottles", "World Scouts");
        movieList.add(movie);

        movie = new DataModel("Scouts transforms recycled bottles into beautiful planters", "World Scouts");
        movieList.add(movie);

        movie = new DataModel("Scouts transforms recycled bottles into beautiful planters", "World Scouts");
        movieList.add(movie);

        movie = new DataModel("Scouts transforms recycled bottles into beautiful planters", "World Scouts");
        movieList.add(movie);

        movie = new DataModel("Scouts transforms recycled bottles into beautiful planters", "World Scouts");
        movieList.add(movie);

        movie = new DataModel("Scouts transforms recycled bottles into beautiful planters", "World Scouts");
        movieList.add(movie);

        movie = new DataModel("Scouts transforms recycled bottles into beautiful planters", "World Scouts");
        movieList.add(movie);

        movie = new DataModel("Scouts transforms recycled bottles into beautiful planters", "World Scouts");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

}