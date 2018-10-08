package com.softices.lit.activities;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class LacturesActivity extends AppCompatActivity {
    DataBaseHelper dbH;
    ImageView show_lacture;
    private String strEmail;
    private List<FacultyModel> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lactures);
        dbH = new DataBaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("USER", android.content.Context.MODE_PRIVATE);
        strEmail = sharedPreferences.getString("Email", "");
        Toast.makeText(this, strEmail, Toast.LENGTH_SHORT).show();
        show_lacture = (ImageView) findViewById(R.id.show_lacture);
        retrieveImage(show_lacture);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new adapter(movieList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                FacultyModel movie = movieList.get(position);
                Toast.makeText(LacturesActivity.this, "Is selected", Toast.LENGTH_LONG).show();
                ;
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        retrieveFacultyImage();
    }

    public void retrieveFacultyImage() {
        SQLiteDatabase db = dbH.getWritableDatabase();
        String query = "SELECT * FROM faculty WHERE Email = " + "'" + strEmail + "'";
        Cursor cursor= db.rawQuery(query, null);
        movieList.clear();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    FacultyModel movie = new FacultyModel();
                    //pic1.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                    byte[] data = cursor.getBlob(cursor.getColumnIndex("Image"));
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(data);
                    Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                    movie.setImgBitmap(theImage);
                    // pic1.setImageBitmap(theImage);
                    movieList.add(movie);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        mAdapter.notifyDataSetChanged();
    }


    public byte[] retreiveImageFromDB() {
        try {
            SQLiteDatabase mDb = dbH.getWritableDatabase();

            String query = "SELECT * FROM faculty WHERE Email = " + "''" + strEmail + "'";
            Cursor cur = mDb.rawQuery(query, null);
            movieList.clear();
         /*   Cursor cur = mDb.query(true, dbH.TABLE_FACULTY, new String[]{dbH.FACULTY_ADD_LACTURE},
                    dbH.FACULTY_EMAIL + " = '" + strEmail + "'", null, null, null,
                    null, "1");*/
            if (cur.moveToFirst()) {
                byte[] blob = cur.getBlob(cur.getColumnIndex(dbH.FACULTY_ADD_LACTURE));
                cur.close();
                return blob;
            }
            cur.close();
        } catch (Exception e) {
            Log.e("LacturesActivity", "retreiveImageFromDB" + e);
        }
        return null;
    }

    public void retrieveImage(ImageView imageView) {
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from faculty where Email=?", new String[]{strEmail});
        Log.e("__________________", cursor.getCount() + "");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(dbH.FACULTY_ADD_LACTURE));
                imageView.setImageBitmap(DbBitmapUtility.getImage(blob));
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "no image found", Toast.LENGTH_LONG).show();
        }
    }
}
