package com.softices.lit.activities;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.softices.lit.R;
import com.softices.lit.database.DataBaseHelper;
import com.softices.lit.methods.DbBitmapUtility;
import com.softices.lit.models.FacultyModel;
import com.softices.lit.models.StudentModel;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class Stu_List extends AppCompatActivity {
    DataBaseHelper dbH;
    ImageView show_lacture;
    private String strEmail;
    private List<StudentModel> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Textadapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__list_);
        dbH = new DataBaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("USER", android.content.Context.MODE_PRIVATE);
        strEmail = sharedPreferences.getString("Email", "");
        Toast.makeText(this, strEmail, Toast.LENGTH_SHORT).show();
        show_lacture = (ImageView) findViewById(R.id.show_lacture);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new Textadapter(dbH.getAllStudent());
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }


}
