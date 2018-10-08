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
import com.softices.lit.models.FacultyModel;

public class FacultyCornerActivity extends AppCompatActivity {
    private DataBaseHelper db;
    TextView firstname, lastname, email, mobile, address, subject, department, degree;
    Button btn_fac_corner_add_lacture,btn_fac_stu_list;
    private String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_corner);
        db = new DataBaseHelper(this);

        firstname = (TextView) findViewById(R.id.fac_corner_ans_firstname);
        lastname = (TextView) findViewById(R.id.fac_corner_ans_lastname);
        email = (TextView) findViewById(R.id.fac_corner_ans_email);
        mobile = (TextView) findViewById(R.id.fac_corner_ans_mobile);
        address = (TextView) findViewById(R.id.fac_corner_ans_address);
        subject = (TextView) findViewById(R.id.fac_corner_ans_subject);
        department = (TextView) findViewById(R.id.fac_corner_ans_department);
        degree = (TextView) findViewById(R.id.fac_corner_ans_degree);

        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        strEmail = sharedPreferences.getString("Email", "");
        setdata();
        updataedata();

        btn_fac_corner_add_lacture = (Button) findViewById(R.id.btn_fac_corner_add_lacture);
        btn_fac_corner_add_lacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacultyCornerActivity.this, AddLactureFacultyActivity.class);
                startActivity(intent);
                Log.e("LIT", "btn_creatacc was clicked");
                finish();
            }
        });
        btn_fac_stu_list = (Button) findViewById(R.id.btn_fac_stu_list);
        btn_fac_stu_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacultyCornerActivity.this, Stu_List.class);
                startActivity(intent);
                Log.e("LIT", "btn_creatacc was clicked");
            }
        });
    }

    private void updataedata() {
        FacultyModel facultyModel = updatedFacultyFromDatabase(strEmail);
        firstname.setText(facultyModel.getFirstname());
        lastname.setText(facultyModel.getLastname());
        email.setText(facultyModel.getEmail());
        mobile.setText(facultyModel.getMobile());
        address.setText(facultyModel.getAddress());
        subject.setText(facultyModel.getSubject());
        department.setText(facultyModel.getDepartment());
        degree.setText(facultyModel.getDegree());

    }

    private void setdata() {
        FacultyModel facultyModel = showFacultyFromDatabase(strEmail);
        firstname.setText(facultyModel.getFirstname());
        lastname.setText(facultyModel.getLastname());
        email.setText(facultyModel.getEmail());
        mobile.setText(facultyModel.getMobile());
        address.setText(facultyModel.getAddress());
        subject.setText(facultyModel.getSubject());
        department.setText(facultyModel.getDepartment());
        degree.setText(facultyModel.getDegree());

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
                editprofile();
                break;
            case R.id.logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logout() {
        try {
            SharedPreferences sharedpreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("isLogin", " ");
            editor.commit();

            SQLiteOpenHelper ab;
            SQLiteDatabase logout = db.getWritableDatabase();
            String query = "DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_FACULTY;
            Cursor cursor = logout.rawQuery(query, null);
        } catch (Exception e) {
            Log.e("mye", "onUpgrade" + e);
        }
        startActivity(new Intent(FacultyCornerActivity.this, LoginActivity.class));
        finishAffinity();
    }

    private void editprofile() {
        Intent intent = new Intent(FacultyCornerActivity.this, EditProfileFacultyActivity.class);
        startActivity(intent);

    }

    private FacultyModel showFacultyFromDatabase(String email) {
        Log.e("email", email);
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorFaculty = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_FACULTY +
                " WHERE " + DataBaseHelper.FACULTY_EMAIL + " = '" + email + "'", null);
        FacultyModel facultyModel = new FacultyModel();
        if (cursorFaculty.moveToFirst()) {
            do {
                facultyModel.setEmail(cursorFaculty.getString(0));
                facultyModel.setFirstname(cursorFaculty.getString(1));
                facultyModel.setLastname(cursorFaculty.getString(2));
                facultyModel.setDepartment(cursorFaculty.getString(4));
                facultyModel.setMobile(cursorFaculty.getString(5));
                facultyModel.setAddress(cursorFaculty.getString(6));
                facultyModel.setDegree(cursorFaculty.getString(7));
                facultyModel.setSubject(cursorFaculty.getString(8));
            } while (cursorFaculty.moveToNext());
        }
        cursorFaculty.close();
        return facultyModel;
    }


    private FacultyModel updatedFacultyFromDatabase(String email) {
        Log.e("email", email);
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorFaculty = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_FACULTY +
                " WHERE " + DataBaseHelper.FACULTY_EMAIL + " = '" + email + "'", null);
        FacultyModel facultyModel = new FacultyModel();
        if (cursorFaculty.moveToFirst()) {
            do {
                facultyModel.setEmail(cursorFaculty.getString(0));
                facultyModel.setFirstname(cursorFaculty.getString(1));
                facultyModel.setLastname(cursorFaculty.getString(2));
                facultyModel.setDepartment(cursorFaculty.getString(4));
                facultyModel.setMobile(cursorFaculty.getString(5));
                facultyModel.setAddress(cursorFaculty.getString(6));
                facultyModel.setDegree(cursorFaculty.getString(7));
                facultyModel.setSubject(cursorFaculty.getString(8));
            } while (cursorFaculty.moveToNext());
        }
        cursorFaculty.close();
        return facultyModel;
    }


}
