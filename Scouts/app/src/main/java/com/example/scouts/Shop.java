package com.example.scouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    private List<DataModel> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataModel shopitem;
    ImageView menu,logout;
    private ShopRecyclerAdaptor mAdapter;
    LinearLayout gallery,shop,event,activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);



        recyclerView = (RecyclerView) findViewById(R.id.shopAdaptor);
        gallery= (LinearLayout) findViewById(R.id.gallery);
        shop= (LinearLayout) findViewById(R.id.shop);
        event= (LinearLayout) findViewById(R.id.events);
        activity= (LinearLayout) findViewById(R.id.activity);


        mAdapter = new ShopRecyclerAdaptor(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        shopitem = new DataModel("Mark Dawson The Cleaner","$0.00");
        mAdapter.addMessage(shopitem);
        shopitem = new DataModel("Mark Dawson The Cleaner","$0.00");
        mAdapter.addMessage(shopitem);
        shopitem = new DataModel("Mark Dawson The Cleaner","$0.00");
        mAdapter.addMessage(shopitem);
        shopitem = new DataModel("Mark Dawson The Cleaner","$0.00");
        mAdapter.addMessage(shopitem);
        shopitem = new DataModel("Mark Dawson The Cleaner","$0.00");
        mAdapter.addMessage(shopitem);





        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Shop.this, galleryview.class);
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Shop.this, MainActivity.class);
                startActivity(intent);
            }
        });



        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Shop.this, MainActivity.class);
                startActivity(intent);
            }
        });

        logout = (ImageView) findViewById(R.id.menuBtn);
        menu = (ImageView) findViewById(R.id.contactForm);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, Contact.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(Shop.this, userlogin.class));
            }
        });

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
