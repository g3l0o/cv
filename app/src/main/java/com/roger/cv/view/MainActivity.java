package com.roger.cv.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.roger.cv.R;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) null);
    }

    /*
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
*/


}
