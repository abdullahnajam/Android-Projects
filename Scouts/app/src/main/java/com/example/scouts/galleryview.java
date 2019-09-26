package com.example.scouts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.stfalcon.frescoimageviewer.ImageViewer;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.stfalcon.frescoimageviewer.ImageViewer;

public class galleryview extends AppCompatActivity {

    GridView androidGridView;

    ImageView selectedImageView,menu,logout;

    LinearLayout gallery,shop,event,activity,bottom,imageOpen;

    Integer[] imageIDs = {
            R.drawable.camp, R.drawable.camp, R.drawable.camp,
            R.drawable.maxresdefault, R.drawable.maxresdefault, R.drawable.maxresdefault,
            R.drawable.scouts, R.drawable.scouts, R.drawable.scouts,
            R.drawable.eventexample, R.drawable.eventexample, R.drawable.eventexample,
            R.drawable.images, R.drawable.images, R.drawable.images,
            R.drawable.camp, R.drawable.camp, R.drawable.camp,
            R.drawable.maxresdefault, R.drawable.maxresdefault, R.drawable.maxresdefault,
            R.drawable.scouts, R.drawable.scouts, R.drawable.scouts,
            R.drawable.eventexample, R.drawable.eventexample, R.drawable.eventexample,
            R.drawable.images, R.drawable.images, R.drawable.images,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitygallery);

        androidGridView = (GridView) findViewById(R.id.gridview_android_example);
        androidGridView.setAdapter(new ImageAdapterGridView(this));

        selectedImageView = (ImageView) findViewById(R.id.selectedImage);

        gallery= (LinearLayout) findViewById(R.id.gallery);
        shop= (LinearLayout) findViewById(R.id.shop);
        event= (LinearLayout) findViewById(R.id.events);
        activity= (LinearLayout) findViewById(R.id.activity);

        bottom= (LinearLayout) findViewById(R.id.bottombar);

        logout = (ImageView) findViewById(R.id.menuBtn);

        imageOpen= (LinearLayout) findViewById(R.id.imageLayout);

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                //Glide.with(galleryview.this).load("http://goo.gl/gEgYUd").into(selectedImageView);
                selectedImageView.setImageResource(imageIDs[position]);
                /*Fresco.initialize(galleryview.this);
                new ImageViewer.Builder(galleryview.this, imageIDs)
                        .show();*/

                androidGridView.setVisibility(View.INVISIBLE);
                bottom.setVisibility(View.INVISIBLE);
                imageOpen.setVisibility(View.VISIBLE);


                //Toast.makeText(getBaseContext(), "Grid Item " + (position + 1) + " Selected", Toast.LENGTH_LONG).show();
            }
        });
        logout = (ImageView) findViewById(R.id.menuBtn);
        menu = (ImageView) findViewById(R.id.contactForm);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(galleryview.this, Contact.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(galleryview.this, userlogin.class));
            }
        });




        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(galleryview.this, Shop.class);
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(galleryview.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imageOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidGridView.setVisibility(View.VISIBLE);
                bottom.setVisibility(View.VISIBLE);
                imageOpen.setVisibility(View.INVISIBLE);
            }
        });



        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(galleryview.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        androidGridView.setVisibility(View.VISIBLE);
        bottom.setVisibility(View.VISIBLE);
        imageOpen.setVisibility(View.INVISIBLE);

    }
    public class ImageAdapterGridView extends BaseAdapter {
        private Context mContext;

        public ImageAdapterGridView(Context c) {
            mContext = c;
        }

        public int getCount() {
            return imageIDs.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView mImageView;

            if (convertView == null) {
                mImageView = new ImageView(mContext);
                mImageView.setLayoutParams(new GridView.LayoutParams(350, 350));
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mImageView.setPadding(5, 5, 5, 10);
            } else {
                mImageView = (ImageView) convertView;
            }
            mImageView.setImageResource(imageIDs[position]);
            return mImageView;
        }
    }
}
