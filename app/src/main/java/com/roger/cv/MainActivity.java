package com.roger.cv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final long ONE_MEGABYTE = 1024 * 1024;

    private List<Company> companies;
    private RecyclerView recyclerViewCompanies;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navigationViewHeader;
    private LinearLayout headerLayout;
    private TextView nameHeaderLayout;
    private TextView mailHeaderLayout;
    private ImageButton profileImage;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCVDatabaseReference;

    private FirebaseStorage storage;
    private StorageReference storageRef;

    private String mUsaerName;

    private Information information;

    private byte[] imageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsaerName = "CV_APP";

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCVDatabaseReference = mFirebaseDatabase.getReference("User/Rogelio");

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://roger-cv.appspot.com");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        recyclerViewCompanies = (RecyclerView) findViewById(R.id.rv_card_view_company);

        navigationViewHeader = navigationView.getHeaderView(0);
        headerLayout = (LinearLayout) navigationViewHeader.findViewById(R.id.headerLayout);
        nameHeaderLayout = (TextView) navigationViewHeader.findViewById(R.id.nameTextView);
        mailHeaderLayout = (TextView) navigationViewHeader.findViewById(R.id.emailTextView);
        profileImage = (ImageButton) navigationViewHeader.findViewById(R.id.profile_image_drawer);

        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCompanies.setLayoutManager(llm);
        recyclerViewCompanies.setHasFixedSize(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), InformationActivity.class);
                in.putExtra("information", information);
                in.putExtra("imageBytes" , imageBytes);

                startActivity(in);
            }
        });

        mCVDatabaseReference.child("information").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                information = dataSnapshot.getValue(Information.class);

                nameHeaderLayout.setText(information.getName());
                mailHeaderLayout.setText(information.getMail());

                StorageReference profileRef = storageRef.child(information.getImageURL());

                profileRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        imageBytes = bytes;
                        ByteArrayInputStream imageStream = new ByteArrayInputStream(bytes);
                        Bitmap bm = BitmapFactory.decodeStream(imageStream);

                        RoundedBitmapDrawable rid = RoundedBitmapDrawableFactory.create(getResources(), bm);
                        rid.setCircular(true);

                        profileImage.setImageDrawable(rid);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mCVDatabaseReference.child("experience").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                companies = new ArrayList<Company>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    final Company company = ds.getValue(Company.class);
                    companies.add(company);
                }

                RVAdapter adapter = new RVAdapter(companies);
                recyclerViewCompanies.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), InformationActivity.class);
                in.putExtra("information", information);
                in.putExtra("imageBytes" , imageBytes);

                startActivity(in);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
