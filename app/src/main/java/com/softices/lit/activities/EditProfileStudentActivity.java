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
import com.softices.lit.models.StudentModel;

public class EditProfileStudentActivity extends AppCompatActivity {
    private DataBaseHelper db;
    EditText firstname, lastname, enrollment, sem, departmnet, mobile, address;
    Button btn_update_student;
    private String strEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_student);
        db = new DataBaseHelper(this);


        firstname = (EditText) findViewById(R.id.edt_stu_ans_first_name);
        lastname = (EditText) findViewById(R.id.edt_stu_ans_last_name);
        enrollment = (EditText) findViewById(R.id.edt_stu_ans_enrollment);
        sem = (EditText) findViewById(R.id.edt_stu_ans_sem);
        departmnet = (EditText) findViewById(R.id.edt_stu_ans_department);
        mobile = (EditText) findViewById(R.id.edt_stu_ans_mobile);
        address = (EditText) findViewById(R.id.edt_stu_ans_address);


        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        strEmail = sharedPreferences.getString("Email", "");
        setdata();

        btn_update_student = (Button) findViewById(R.id.btn_update_stu);
        btn_update_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatestudentData(strEmail,firstname.getText().toString(),lastname.getText().toString(),departmnet.getText().toString(),sem.getText().toString(),enrollment.getText().toString(),mobile.getText().toString(),address.getText().toString());
                Intent intent = new Intent(EditProfileStudentActivity.this, StudentCornerActivity.class);
                startActivity(intent);
                Log.e("LIT", "btn_creatacc was clicked");
                finish();
            }
        });
    }


    private void setdata() {
        StudentModel studentModel = showStudentsFromDatabase(strEmail);
        firstname.setText(studentModel.getFirstName());
        lastname.setText(studentModel.getLastName());
        enrollment.setText(studentModel.getEnrollment());
        sem.setText(studentModel.getSem());
        departmnet.setText(studentModel.getDepartment());
        mobile.setText(studentModel.getMobileNo());
        address.setText(studentModel.getAddress());
        }

    private StudentModel showStudentsFromDatabase(String email) {

        Log.e("email", email);
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        SQLiteDatabase logout = db.getWritableDatabase();
        Cursor cursorStudent = logout.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_STUDENT +
                " where " + DataBaseHelper.STUDENT_EMAIL + " = '" + email + "'", null);

        StudentModel studentModel = new StudentModel();
        //if the cursor has some data
        if (cursorStudent.moveToFirst()) {
            //looping through all the records
            do {

                //pushing each record in the employee list

                studentModel.setFirstName(cursorStudent.getString(1));
                studentModel.setLastName(cursorStudent.getString(2));
                studentModel.setSem(cursorStudent.getString(4));
                studentModel.setDepartment(cursorStudent.getString(5));
                studentModel.setMobileNo(cursorStudent.getString(6));
                studentModel.setAddress(cursorStudent.getString(7));
                studentModel.setEnrollment(cursorStudent.getString(8));


            } while (cursorStudent.moveToNext());
        }
        //closing the cursor
        cursorStudent.close();
        return studentModel;
    }

    public boolean updatestudentData(String email, String firstname, String lastname, String department, String sem, String enrollment, String mobile, String address) {
        SQLiteDatabase sqldb = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.STUDENT_EMAIL, email);
        contentValues.put(DataBaseHelper.STUDENT_FIRST_NAME, firstname);
        contentValues.put(DataBaseHelper.STUDENT_LAST_NAME, lastname);
        contentValues.put(DataBaseHelper.STUDENT_DEPATRMENT, department);
        contentValues.put(DataBaseHelper.STUDENT_SEM, sem);
        contentValues.put(DataBaseHelper.STUDENT_ENROLLMENT, enrollment);
        contentValues.put(DataBaseHelper.STUDENT_MOBILE_NO, mobile);
        contentValues.put(DataBaseHelper.STUDENT_ADDRESS, address);
        sqldb.update(DataBaseHelper.TABLE_STUDENT, contentValues, DataBaseHelper.STUDENT_EMAIL + " = ? ", new String[]{email});
        return true;
    }


}