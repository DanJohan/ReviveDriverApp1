package com.car.revivedriverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.car.revivedriverapp.Adapters.ImagesAdapter;
import com.car.revivedriverapp.Models.UserDetailModel;

import java.util.ArrayList;
import java.util.List;

public class CreateJobCardActivity extends AppCompatActivity {
  UserDetailModel userDetailModel;
  RecyclerView recyclerView;
  ImagesAdapter imagesAdapter;
  List<String > imagesList;
    TextView userName,userPhone,userAdreess,carBrandName,carModelName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job_card);
        recyclerView = (RecyclerView)
                findViewById(R.id.recycler_view);

        userName =(TextView)findViewById(R.id.name);
        userPhone =(TextView)findViewById(R.id.m_number);
        userAdreess =(TextView)findViewById(R.id.adreess);
        carBrandName =(TextView)findViewById(R.id.brand_name);
        carModelName =(TextView)findViewById(R.id.time);
        Intent intent=getIntent();
        userDetailModel = intent.getParcelableExtra("userDetailModel");

        imagesAdapter = new ImagesAdapter(CreateJobCardActivity.this,userDetailModel.getUserCarImages());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(imagesAdapter);

        setUserData();
    }

    public void setUserData(){

        userName.setText(userDetailModel.getUserName());
        userPhone.setText(userDetailModel.getUserPhone());
        userAdreess.setText(userDetailModel.getUserAddress());
        carBrandName.setText(userDetailModel.getUserPickupDate());
        carModelName.setText(userDetailModel.getUserPickupTime());

    }
}
