package com.car.revivedriverapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.car.revivedriverapp.Models.UserDetailModel;
import com.car.revivedriverapp.R;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {

    private ArrayList<UserDetailModel> imgList;
    Context context;
    List<String> carList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public Button cancel;
        public ImageView image;
        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.img);

           // year = (TextView) view.findViewById(R.id.year);
        }
    }


    public ImagesAdapter(Context context,List<String> carList) {
        this.context =context;
        this.carList = carList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
       String movie = carList.get(position);
        Glide.with(context).load(movie).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}