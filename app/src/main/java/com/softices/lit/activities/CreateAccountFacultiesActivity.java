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

public class CreateAccountFacultiesActivity extends AppCompatActivity {

    Button btn_signup_fac;
    Spinner spinner_dept;
    EditText first_name, last_name, email, password, confpass, mobile_no, address, degree, subject;
    private DataBaseHelper fac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_faculties);

        fac = new DataBaseHelper(this);
        btn_signup_fac = (Button) findViewById(R.id.btn_signup_faculty);
        password = (EditText) findViewById(R.id.fac_password);
        confpass = (EditText) findViewById(R.id.fac_confpass);
        email = (EditText) findViewById(R.id.fac_email);
        first_name = (EditText) findViewById(R.id.fac_firstname);
        last_name = (EditText) findViewById(R.id.fac_lastname);
        mobile_no = (EditText) findViewById(R.id.fac_mobile);
        address = (EditText) findViewById(R.id.fac_address);
        degree = (EditText) findViewById(R.id.fac_Degree);
        spinner_dept = (Spinner) findViewById(R.id.fac_spinner_dept);
        subject = (EditText) findViewById(R.id.fac_sub);

        email.setText("s@s.s");
        password.setText("123456");
        confpass.setText("123456");
        first_name.setText("s");
        last_name.setText("s");
        mobile_no.setText("1234567890");
        address.setText("asd");
        degree.setText("be");
        subject.setText("maths");

        btn_signup_fac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Email_Pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

                if (first_name.length() < 1) {
                    Toast.makeText(CreateAccountFacultiesActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (last_name.length() < 1) {
                    Toast.makeText(CreateAccountFacultiesActivity.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 5 && password.getText().toString().contains(" ")) {
                    Toast.makeText(CreateAccountFacultiesActivity.this, "password must be atleast 6", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().equals(confpass.getText().toString())) {
                    Toast.makeText(CreateAccountFacultiesActivity.this, "password must be same", Toast.LENGTH_SHORT).show();
                } else if (!email.getText().toString().matches(Email_Pattern)) {
                    Toast.makeText(CreateAccountFacultiesActivity.this, "Enter Valid Email Address", Toast.LENGTH_LONG).show();
                } else if (degree.length() < 1) {
                    Toast.makeText(CreateAccountFacultiesActivity.this, "ENTER CORRECT DEGREE", Toast.LENGTH_SHORT).show();
                } else if (mobile_no.length() != 10) {
                    Toast.makeText(CreateAccountFacultiesActivity.this, "ENTER CORRECT MOBILE NO.", Toast.LENGTH_SHORT).show();
                } else if (address.length() < 1) {
                    Toast.makeText(CreateAccountFacultiesActivity.this, "ENTER ADDRESS", Toast.LENGTH_SHORT).show();
                } else {
                    insertUser();
                    Intent intent = new Intent(CreateAccountFacultiesActivity.this, LoginActivity.class);
                    intent.putExtra("data", true);
                    startActivity(intent);
                    finishAffinity();
                }


            }
        });
    }

    public void insertUser() {
        try {
            // get writable database as we want to write data
            SQLiteDatabase mDatabase = fac.getReadableDatabase();
            ContentValues values = new ContentValues();
            // `id` and `timestamp` will be inserted automatically.
            // no need to add them
            values.put(DataBaseHelper.FACULTY_FIRST_NAME, first_name.getText().toString());
            values.put(DataBaseHelper.FACULTY_LAST_NAME, last_name.getText().toString());
            values.put(DataBaseHelper.FACULTY_PASSWORD, password.getText().toString());
            values.put(DataBaseHelper.FACULTY_EMAIL, email.getText().toString());
            values.put(DataBaseHelper.FACULTY_DEPARTMENT, spinner_dept.getSelectedItem().toString());
            values.put(DataBaseHelper.FACULTY_DEGREE, degree.getText().toString());
            values.put(DataBaseHelper.FACULTY_MOBILE_NO, mobile_no.getText().toString());
            values.put(DataBaseHelper.FACULTY_ADDRESS, address.getText().toString());
            values.put(DataBaseHelper.FACULTY_SUBJECTS, subject.getText().toString());


            // insert row
            mDatabase.insert(DataBaseHelper.TABLE_FACULTY, null, values);
            // close db connection
            fac.close();
            // return newly inserted row id

        } catch (Exception e) {
            Log.e("mye", "insertUser" + e);
        }
    }
}



