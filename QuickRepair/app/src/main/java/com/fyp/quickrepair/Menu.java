package com.fyp.quickrepair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fyp.quickrepair.Model.Users;
import com.fyp.quickrepair.Model.Values;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    CardView search,stats,booking;
    CardView rent,mechanic,oil,other,carwash;
    TextView email,phone,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkbleue)); //status bar or the time bar at the top
        }
        inflatingViews();
        onclickinflation();
        displayCustomerData();

    }

    public void displayCustomerData()
    {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        email.setText(user.getEmail());

        DatabaseReference datausers = FirebaseDatabase.getInstance().getReference().child("UserData").child(user.getUid());
        datausers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users users=dataSnapshot.getValue(Users.class);
                phone.setText(users.getPhone());
                name.setText(users.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onclickinflation()
    {
        search.setOnClickListener(this);
        booking.setOnClickListener(this);
        stats.setOnClickListener(this);

        rent.setOnClickListener(this);
        oil.setOnClickListener(this);
        other.setOnClickListener(this);
        carwash.setOnClickListener(this);
        mechanic.setOnClickListener(this);
    }

    public void inflatingViews()
    {
        email=(TextView) findViewById(R.id.emailid);
        phone=(TextView) findViewById(R.id.phonenumber);
        name=(TextView) findViewById(R.id.name);

        search=(CardView) findViewById(R.id.searchNearby);
        stats=(CardView) findViewById(R.id.stats);
        booking=(CardView) findViewById(R.id.booking);

        rent=(CardView) findViewById(R.id.rentcar);
        mechanic=(CardView) findViewById(R.id.mechanic);
        oil=(CardView) findViewById(R.id.oilchangecard);
        other=(CardView) findViewById(R.id.other);
        carwash=(CardView) findViewById(R.id.carwash);


    }

    @Override
    public void onClick(View v) {

        if(v==search)
        {
            startActivity(new Intent(Menu.this, ServicesListView.class));
        }
        if(v==booking)
        {
            startActivity(new Intent(Menu.this, Bookings.class));
        }
        if(v==stats)
        {
            startActivity(new Intent(Menu.this, Stats.class));
        }
        if(v==rent)
        {
            Values values=new Values();
            Intent intent = new Intent(Menu.this, CategoryServiceList.class);
            intent.putExtra(values.getSEARCH_CATEGORY_TAG(),values.getRENT_CATEGORY());
            startActivity(intent);
        }
        if(v==carwash)
        {
            Values values=new Values();
            Intent intent = new Intent(Menu.this, CategoryServiceList.class);
            intent.putExtra(values.getSEARCH_CATEGORY_TAG(),values.getCARWASH_CATEGORY());
            startActivity(intent);
        }
        if(v==oil)
        {
            Values values=new Values();
            Intent intent = new Intent(Menu.this, CategoryServiceList.class);
            intent.putExtra(values.getSEARCH_CATEGORY_TAG(),values.getOIL_CATEGORY());
            startActivity(intent);
        }
        if(v==mechanic)
        {
            Values values=new Values();
            Intent intent = new Intent(Menu.this, CategoryServiceList.class);
            intent.putExtra(values.getSEARCH_CATEGORY_TAG(),values.getMECHANIC_CATEGORY());
            startActivity(intent);
        }
        if(v==other)
        {
            Values values=new Values();
            Intent intent = new Intent(Menu.this, CategoryServiceList.class);
            intent.putExtra(values.getSEARCH_CATEGORY_TAG(),values.getOTHER_CATEGORY());
            startActivity(intent);
        }


    }
}
