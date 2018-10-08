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
import com.softices.lit.models.HODModel;

public class EditProfileHODActivity extends AppCompatActivity {
    private DataBaseHelper db;
    EditText firstname,lastname,department,subject,address,degree,mobile;
    Button btn_update_hod;
    private String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_hod);
        db = new DataBaseHelper(this);


        firstname = (EditText) findViewById(R.id.edt_hod_ans_first_name);
        lastname = (EditText) findViewById(R.id.edt_hod_ans_last_name);
        department = (EditText) findViewById(R.id.edt_hod_ans_department);
        subject = (EditText) findViewById(R.id.edt_hod_ans_sub);
        address = (EditText) findViewById(R.id.edt_hod_ans_address);
        degree = (EditText) findViewById(R.id.edt_hod_ans_degree);
        mobile = (EditText) findViewById(R.id.edt_hod_ans_mobile);


        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        strEmail = sharedPreferences.getString("Email", "");
        setdata();
        btn_update_hod = (Button) findViewById(R.id.btn_update_hod);
        btn_update_hod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatehodData(strEmail,firstname.getText().toString(),lastname.getText().toString(),department.getText().toString(),subject.getText().toString(),degree.getText().toString(),mobile.getText().toString(),address.getText().toString());
                Intent intent = new Intent(EditProfileHODActivity.this, HODCornerActivity.class);
                startActivity(intent);
                Log.e("LIT", "btn_creatacc was clicked");
                finish();
            }
        });
    }

    private void setdata() {
        HODModel hodModel = showHODFromDatabase(strEmail);
        firstname.setText(hodModel.getFirstname());
        lastname.setText(hodModel.getLastname());
        department.setText(hodModel.getDepartment());
        degree.setText(hodModel.getDegree());
        address.setText(hodModel.getAddress());
        subject.setText(hodModel.getSubject());
        mobile.setText(hodModel.getMobile());

    }

    private HODModel showHODFromDatabase(String email) {
        Log.e("Email", email);
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorHOD = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_HOD +
                " WHERE " + DataBaseHelper.HOD_EMAIL + " = '" + email + "'", null);
        HODModel hodModel = new HODModel();
        if (cursorHOD.moveToFirst()) {
            do {
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

    public boolean updatehodData(String email,String firstname,String lastname,String department,String subject,String degree,String mobile,String address) {
        SQLiteDatabase sqldb = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.HOD_EMAIL,email);
        contentValues.put(DataBaseHelper.HOD_FIRST_NAME,firstname);
        contentValues.put(DataBaseHelper.HOD_LAST_NAME,lastname);
        contentValues.put(DataBaseHelper.HOD_DEPARTMENT,department);
        contentValues.put(DataBaseHelper.HOD_SUBJECTS,subject);
        contentValues.put(DataBaseHelper.HOD_DEGREE,degree);
        contentValues.put(DataBaseHelper.HOD_MOBILE_NO,mobile);
        contentValues.put(DataBaseHelper.HOD_ADDRESS,address);
        sqldb.update(DataBaseHelper.TABLE_HOD, contentValues, DataBaseHelper.HOD_EMAIL + " = ? " ,new String[] { email });
        return true;
    }

}
