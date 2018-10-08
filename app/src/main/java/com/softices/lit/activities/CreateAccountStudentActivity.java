package com.softices.lit.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.softices.lit.R;
import com.softices.lit.database.DataBaseHelper;

public class CreateAccountStudentActivity extends AppCompatActivity {
    Button btn_signup_student;
    Spinner spinner_sem, spinner_dept;
    EditText first_name, last_name, email, password, confpass, enrollment, mobile_no, address;
    private DataBaseHelper stu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student_account);

        stu = new DataBaseHelper(this);
        btn_signup_student = (Button) findViewById(R.id.btn_signup_student);
        password = (EditText) findViewById(R.id.std_password);
        confpass = (EditText) findViewById(R.id.std_confpass);
        email = (EditText) findViewById(R.id.std_email);
        first_name = (EditText) findViewById(R.id.std_firstname);
        last_name = (EditText) findViewById(R.id.std_lastname);
        enrollment = (EditText) findViewById(R.id.std_enrollment);
        mobile_no = (EditText) findViewById(R.id.std_mobile);
        address = (EditText) findViewById(R.id.std_address);
        spinner_sem = (Spinner) findViewById(R.id.spinner_sem);
        spinner_dept = (Spinner) findViewById(R.id.spinner_dept);

        email.setText("a@a.a");
        first_name.setText("Akshay");
        last_name.setText("patel");
        password.setText("123456");
        confpass.setText("123456");
        enrollment.setText("160860107013");
        mobile_no.setText("9099123907");
        address.setText("Bhilad");


        btn_signup_student.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Email_Pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

                if (first_name.length() < 1) {
                    Toast.makeText(CreateAccountStudentActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (last_name.length() < 1) {
                    Toast.makeText(CreateAccountStudentActivity.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 5 && password.getText().toString().contains(" ")) {
                    Toast.makeText(CreateAccountStudentActivity.this, "password must be atleast 6", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().equals(confpass.getText().toString())) {
                    Toast.makeText(CreateAccountStudentActivity.this, "password must be same", Toast.LENGTH_SHORT).show();
                } else if (!email.getText().toString().matches(Email_Pattern)) {
                    Toast.makeText(CreateAccountStudentActivity.this, "Enter Valid Email Address", Toast.LENGTH_LONG).show();
                } else if (enrollment.length() != 12) {
                    Toast.makeText(CreateAccountStudentActivity.this, "ENTER CORRECT ENROLLMENT NO.", Toast.LENGTH_SHORT).show();
                } else if (mobile_no.length() != 10) {
                    Toast.makeText(CreateAccountStudentActivity.this, "ENTER CORRECT MOBILE NO.", Toast.LENGTH_SHORT).show();
                } else if (address.length() < 1) {
                    Toast.makeText(CreateAccountStudentActivity.this, "ENTER ADDRESS", Toast.LENGTH_SHORT).show();
                } else {
                    if (insertUser()) {
                        Toast.makeText(CreateAccountStudentActivity.this, "Student created.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateAccountStudentActivity.this, LoginActivity.class);
                        intent.putExtra("data", true);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        Toast.makeText(CreateAccountStudentActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean insertUser() {
        try {
            // get writable database as we want to write data
            SQLiteDatabase mDatabase = stu.getReadableDatabase();
            ContentValues values = new ContentValues();
            // `id` and `timestamp` will be inserted automatically.
            // no need to add them
            values.put(DataBaseHelper.STUDENT_FIRST_NAME, first_name.getText().toString());
            values.put(DataBaseHelper.STUDENT_LAST_NAME, last_name.getText().toString());
            values.put(DataBaseHelper.STUDENT_PASSWORD, password.getText().toString());
            values.put(DataBaseHelper.STUDENT_EMAIL, email.getText().toString());
            values.put(DataBaseHelper.STUDENT_DEPATRMENT, spinner_dept.getSelectedItem().toString());
            values.put(DataBaseHelper.STUDENT_SEM, spinner_sem.getSelectedItem().toString());
            values.put(DataBaseHelper.STUDENT_ENROLLMENT, enrollment.getText().toString());
            values.put(DataBaseHelper.STUDENT_MOBILE_NO, mobile_no.getText().toString());
            values.put(DataBaseHelper.STUDENT_ADDRESS, address.getText().toString());

            // insert row
            mDatabase.insert(DataBaseHelper.TABLE_STUDENT, null, values);
            // close db connection
            stu.close();
            // return newly inserted row id
            return true;
        } catch (Exception e) {
            Log.e("mye", "insertUser" + e);
            return false;
        }
    }
}

