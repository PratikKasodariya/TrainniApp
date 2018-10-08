package com.softices.lit.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softices.lit.R;
import com.softices.lit.database.DataBaseHelper;
import com.softices.lit.models.FacultyModel;

public class EditProfileFacultyActivity extends AppCompatActivity {
    private DataBaseHelper db;
    EditText firstname, lastname, subject, mobile, address, department, degree;
    Button btn_update_faculty;
    private String strEmail;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_faculty);
        db = new DataBaseHelper(this);

        firstname = (EditText) findViewById(R.id.edt_fac_ans_first_name);
        lastname = (EditText) findViewById(R.id.edt_fac_ans_last_name);
        subject = (EditText) findViewById(R.id.edt_fac_ans_sub);
        mobile = (EditText) findViewById(R.id.edt_fac_ans_mobile);
        address = (EditText) findViewById(R.id.edt_fac_ans_address);
        department = (EditText) findViewById(R.id.edt_fac_ans_department);
        degree = (EditText) findViewById(R.id.edt_fac_ans_degree);

        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        strEmail = sharedPreferences.getString("Email", "");

        setData();

        btn_update_faculty = (Button) findViewById(R.id.btn_update_faculty);


        btn_update_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatefacultyData(strEmail,firstname.getText().toString(),lastname.getText().toString(),department.getText().toString(),subject.getText().toString(),mobile.getText().toString(),degree.getText().toString(),address.getText().toString());
                Intent intent = new Intent(EditProfileFacultyActivity.this, FacultyCornerActivity.class);
                startActivity(intent);
                Log.e("LIT", "btn_creatacc was clicked");
                finish();
            }
        });

    }

    private void setData() {
        FacultyModel facultyModel = showFacultyFromDatabase(strEmail);
        firstname.setText(facultyModel.getFirstname());
        lastname.setText(facultyModel.getLastname());
        subject.setText(facultyModel.getSubject());
        mobile.setText(facultyModel.getMobile());
        address.setText(facultyModel.getAddress());
        department.setText(facultyModel.getDepartment());
        degree.setText(facultyModel.getDegree());
    }

    private FacultyModel showFacultyFromDatabase(String email) {
        Log.e("email", email);
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorFaculty = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_FACULTY +
                " WHERE " + DataBaseHelper.FACULTY_EMAIL + " = '" + email + "'", null);
        FacultyModel facultyModel = new FacultyModel();
        if (cursorFaculty.moveToFirst()) {
            do {
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


    public boolean updatefacultyData(String email,String firstname,String lastname,String department,String subject,String mobile,String degree,String address) {
        SQLiteDatabase sqldb = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.FACULTY_EMAIL,email);
        contentValues.put(DataBaseHelper.FACULTY_FIRST_NAME,firstname);
        contentValues.put(DataBaseHelper.FACULTY_LAST_NAME,lastname);
        contentValues.put(DataBaseHelper.FACULTY_DEPARTMENT,department);
        contentValues.put(DataBaseHelper.FACULTY_SUBJECTS,subject);
        contentValues.put(DataBaseHelper.FACULTY_MOBILE_NO,mobile);
        contentValues.put(DataBaseHelper.FACULTY_DEGREE,degree);
        contentValues.put(DataBaseHelper.FACULTY_ADDRESS,address);
        sqldb.update(DataBaseHelper.TABLE_FACULTY, contentValues, DataBaseHelper.FACULTY_EMAIL + " = ? " ,new String[] { email });
        return true;
    }

}






