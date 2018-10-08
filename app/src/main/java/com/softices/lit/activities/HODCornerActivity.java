package com.softices.lit.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.softices.lit.R;
import com.softices.lit.database.DataBaseHelper;
import com.softices.lit.models.HODModel;


public class HODCornerActivity extends AppCompatActivity {
    private DataBaseHelper db;
    TextView firstname, lastname, department, subject, mobile, address, email, degree;
    Button btn_hod_corner_add_lacture, btn_hod_corner_add_timetable;
    private String strEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodcorner);
        db = new DataBaseHelper(this);

        firstname = (TextView) findViewById(R.id.hod_corner_ans_firstname);
        lastname = (TextView) findViewById(R.id.hod_corner_ans_lastname);
        department = (TextView) findViewById(R.id.hod_corner_ans_departmnet);
        mobile = (TextView) findViewById(R.id.hod_corner_ans_mobile);
        address = (TextView) findViewById(R.id.hod_corner_ans_address);
        email = (TextView) findViewById(R.id.hod_corner_ans_email);
        degree = (TextView) findViewById(R.id.hod_corner_ans_degree);
        subject = (TextView) findViewById(R.id.hod_corner_ans_subject);

        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        strEmail = sharedPreferences.getString("Email", "");
        setdata();
        updateddata();
        btn_hod_corner_add_lacture = (Button) findViewById(R.id.btn_hod_corner_add_lacture);

        btn_hod_corner_add_lacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HODCornerActivity.this, AddLactureHODActivity.class);
                startActivity(intent);
                Log.e("LIT", "btn_creatacc was clicked");
                finish();
            }
        });

        btn_hod_corner_add_timetable = (Button) findViewById(R.id.btn_hod_corner_add_timetable);


        btn_hod_corner_add_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HODCornerActivity.this, TimetableActivity.class);
                startActivity(intent);
                Log.e("LIT", "btn_creatacc was clicked");
            }
        });


    }

    private void updateddata() {
        HODModel hodModel = updateHODFromDatabase(strEmail);
        firstname.setText(hodModel.getFirstname());
        lastname.setText(hodModel.getLastname());
        department.setText(hodModel.getDepartment());
        mobile.setText(hodModel.getMobile());
        address.setText(hodModel.getAddress());
        email.setText(hodModel.getEmail());
        degree.setText(hodModel.getDegree());
        subject.setText(hodModel.getSubject());
    }


    private void setdata() {
        HODModel hodModel = showHODFromDatabase(strEmail);
        firstname.setText(hodModel.getFirstname());
        lastname.setText(hodModel.getLastname());
        department.setText(hodModel.getDepartment());
        mobile.setText(hodModel.getMobile());
        address.setText(hodModel.getAddress());
        email.setText(hodModel.getEmail());
        degree.setText(hodModel.getDegree());
        subject.setText(hodModel.getSubject());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_profile:
                editprofie();
                break;
            case R.id.logout:
                logout();
                break;
        }
        return true;
    }

    private void logout() {
        try {
            SharedPreferences sharedpreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("isLogin", " ");
            editor.commit();

            SQLiteOpenHelper ab;
            SQLiteDatabase logout = db.getWritableDatabase();
            String query = "DROP TABLE IF EXISTS" + DataBaseHelper.TABLE_STUDENT;
            Cursor cursor = logout.rawQuery(query, null);
        } catch (Exception e) {
            Log.e("mye", "onUpgrade" + e);
        }
        startActivity(new Intent(HODCornerActivity.this, LoginActivity.class));
        finishAffinity();
    }


    private void editprofie() {
        Intent intent = new Intent(HODCornerActivity.this, EditProfileHODActivity.class);
        startActivity(intent);

    }

    private HODModel showHODFromDatabase(String email) {
        Log.e("Email", email);
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorHOD = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_HOD +
                " WHERE " + DataBaseHelper.HOD_EMAIL + " = '" + email + "'", null);
        HODModel hodModel = new HODModel();
        if (cursorHOD.moveToFirst()) {
            do {
                hodModel.setEmail(cursorHOD.getString(0));
                hodModel.setFirstname(cursorHOD.getString(1));
                hodModel.setLastname(cursorHOD.getString(2));
                hodModel.setDepartment(cursorHOD.getString(4));
                hodModel.setMobile(cursorHOD.getString(5));
                hodModel.setAddress(cursorHOD.getString(6));
                hodModel.setDegree(cursorHOD.getString(7));
                hodModel.setSubject(cursorHOD.getString(8));
            } while (cursorHOD.moveToNext());
        }
        cursorHOD.close();
        return hodModel;
    }

    private HODModel updateHODFromDatabase(String email) {
        Log.e("Email", email);
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorHOD = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_HOD +
                " WHERE " + DataBaseHelper.HOD_EMAIL + " = '" + email + "'", null);
        HODModel hodModel = new HODModel();
        if (cursorHOD.moveToFirst()) {
            do {
                hodModel.setEmail(cursorHOD.getString(0));
                hodModel.setFirstname(cursorHOD.getString(1));
                hodModel.setLastname(cursorHOD.getString(2));
                hodModel.setDepartment(cursorHOD.getString(4));
                hodModel.setMobile(cursorHOD.getString(5));
                hodModel.setAddress(cursorHOD.getString(6));
                hodModel.setDegree(cursorHOD.getString(7));
                hodModel.setSubject(cursorHOD.getString(8));
            } while (cursorHOD.moveToNext());
        }
        cursorHOD.close();
        return hodModel;
    }
}
