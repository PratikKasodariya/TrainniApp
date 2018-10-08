package com.softices.lit.activities;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.softices.lit.R;
import com.softices.lit.models.FacultyModel;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    private List<FacultyModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.img_movie);
        }
    }


    public adapter(List<FacultyModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_movie__list__raw, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FacultyModel facultyModel = moviesList.get(position);
        holder.imageView.setImageBitmap(facultyModel.getImgBitmap());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}