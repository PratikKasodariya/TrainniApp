package com.softices.lit.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.softices.lit.R;
import com.softices.lit.models.FacultyModel;
import com.softices.lit.models.StudentModel;

import java.util.List;

public class Textadapter extends RecyclerView.Adapter<Textadapter.MyViewHolder> {

    private List<StudentModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_list_ans_name,txt_list_ans_email,txt_list_ans_mobile,txt_list_ans_enrollment;

        public MyViewHolder(View view) {
            super(view);
            txt_list_ans_name = (TextView) view.findViewById(R.id.txt_list_ans_name);
            txt_list_ans_email= (TextView) view.findViewById(R.id.txt_list_ans_email);
            txt_list_ans_mobile= (TextView) view.findViewById(R.id.txt_list_ans_mobile);
            txt_list_ans_enrollment = (TextView) view.findViewById(R.id.txt_list_ans_enrollment);
        }
    }


    public Textadapter(List<StudentModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_content_movie_list_raw, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StudentModel studentModel = moviesList.get(position);
        holder.txt_list_ans_name.setText(studentModel.getFirstName()+" "+studentModel.getLastName());
        holder.txt_list_ans_email.setText(studentModel.getEmail());
        holder.txt_list_ans_mobile.setText(studentModel.getMobileNo());
        holder.txt_list_ans_enrollment.setText(studentModel.getEnrollment());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}