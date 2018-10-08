package com.softices.lit.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.softices.lit.R;
import com.softices.lit.database.DataBaseHelper;
import com.softices.lit.models.StudentModel;

public class StudentCornerActivity extends AppCompatActivity {
    private DataBaseHelper db;
    TextView address, email, firstname, lastname, mobile, enrollment, cpi, sem, department;
    private String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_corner);
        db = new DataBaseHelper(this);


        address = (TextView) findViewById(R.id.stu_corner_ans_address);
        email = (TextView) findViewById(R.id.stu_corner_ans_email);
        firstname = (TextView) findViewById(R.id.stu_corner_ans_firstname);
        lastname = (TextView) findViewById(R.id.stu_corner_ans_lastname);
        mobile = (TextView) findViewById(R.id.stu_corner_ans_mobile);
        enrollment = (TextView) findViewById(R.id.stu_corner_ans_enrollment);
        cpi = (TextView) findViewById(R.id.stu_corner_ans_cpi);
        sem = (TextView) findViewById(R.id.stu_corner_ans_sem);
        department = (TextView) findViewById(R.id.stu_corner_ans_department);

        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        strEmail = sharedPreferences.getString("Email", "");
        setdata();
        updateddata();


    }

    private void updateddata() {
        StudentModel studentModel = updateStudentsFromDatabase(strEmail);
        address.setText(studentModel.getAddress());
        email.setText(studentModel.getEmail());
        firstname.setText(studentModel.getFirstName());
        lastname.setText(studentModel.getLastName());
        mobile.setText(studentModel.getMobileNo());
        enrollment.setText(studentModel.getEnrollment());
        cpi.setText(studentModel.getCpi());
        sem.setText(studentModel.getSem());
        department.setText(studentModel.getDepartment());


    }

    private void setdata() {
        StudentModel studentModel = showStudentsFromDatabase(strEmail);
        address.setText(studentModel.getAddress());
        email.setText(studentModel.getEmail());
        firstname.setText(studentModel.getFirstName());
        lastname.setText(studentModel.getLastName());
        mobile.setText(studentModel.getMobileNo());
        enrollment.setText(studentModel.getEnrollment());
        cpi.setText(studentModel.getCpi());
        sem.setText(studentModel.getSem());
        department.setText(studentModel.getDepartment());

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
        return true;
    }

    private void logout() {
        {
            try {
                SharedPreferences sharedpreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("isLogin", " ");
                editor.commit();

                SQLiteDatabase logout = db.getWritableDatabase();
                String query = "DROP TABLE IF EXISTS studenr" + DataBaseHelper.TABLE_STUDENT;
                Cursor cursor = logout.rawQuery(query, null);
            } catch (Exception e) {
                Log.e("mye", "onUpgrade" + e);
            }
            startActivity(new Intent(StudentCornerActivity.this, LoginActivity.class));
            finishAffinity();
        }
    }

    private void editprofile() {
        Intent intent = new Intent(StudentCornerActivity.this, EditProfileStudentActivity.class);
        startActivity(intent);

    }


    private StudentModel showStudentsFromDatabase(String email) {

        Log.e("email", email);
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorStudent = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_STUDENT +
                " where " + DataBaseHelper.STUDENT_EMAIL + " = '" + email + "'", null);
        StudentModel studentModel = new StudentModel();
        if (cursorStudent.moveToFirst()) {
            do {
                studentModel.setEmail(cursorStudent.getString(0));
                studentModel.setFirstName(cursorStudent.getString(1));
                studentModel.setLastName(cursorStudent.getString(2));
                studentModel.setSem(cursorStudent.getString(4));
                studentModel.setDepartment(cursorStudent.getString(5));
                studentModel.setMobileNo(cursorStudent.getString(6));
                studentModel.setAddress(cursorStudent.getString(7));
                studentModel.setEnrollment(cursorStudent.getString(8));
                studentModel.setCpi(cursorStudent.getString(9));

            } while (cursorStudent.moveToNext());
        }
        cursorStudent.close();
        return studentModel;
    }

    private StudentModel updateStudentsFromDatabase(String email) {

        Log.e("email", email);
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorStudent = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_STUDENT +
                " where " + DataBaseHelper.STUDENT_EMAIL + " = '" + email + "'", null);

        StudentModel studentModel = new StudentModel();
        if (cursorStudent.moveToFirst()) {
            do {
                studentModel.setEmail(cursorStudent.getString(0));
                studentModel.setFirstName(cursorStudent.getString(1));
                studentModel.setLastName(cursorStudent.getString(2));
                studentModel.setSem(cursorStudent.getString(4));
                studentModel.setDepartment(cursorStudent.getString(5));
                studentModel.setMobileNo(cursorStudent.getString(6));
                studentModel.setAddress(cursorStudent.getString(7));
                studentModel.setEnrollment(cursorStudent.getString(8));
                studentModel.setCpi(cursorStudent.getString(9));

            } while (cursorStudent.moveToNext());
        }
        cursorStudent.close();
        return studentModel;
    }
}