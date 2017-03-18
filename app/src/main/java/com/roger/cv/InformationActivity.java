package com.roger.cv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class InformationActivity extends AppCompatActivity {

    private Information information;
    private TextView TextViewName;
    private TextView TextViewTitle;

    private ImageButton profileImage;

    private TextView TextViewAddress;

    private byte[] imageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextViewName = (TextView) findViewById(R.id.user_profile_name);
        TextViewTitle = (TextView) findViewById(R.id.user_profile_title);
        TextViewAddress = (TextView) findViewById(R.id.information_address);
        profileImage = (ImageButton) findViewById(R.id.user_profile_image);

        information = getIntent().getExtras().getParcelable("information");
        imageBytes = getIntent().getExtras().getByteArray("imageBytes");

        TextViewName.setText(information.getName());
        TextViewTitle.setText(information.getTitle());
        TextViewAddress.setText(information.getAddress());

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageBytes);
        Bitmap bm = BitmapFactory.decodeStream(imageStream);

        RoundedBitmapDrawable rid = RoundedBitmapDrawableFactory.create(getResources(), bm);
        rid.setCircular(true);


        profileImage.setImageDrawable(rid);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
