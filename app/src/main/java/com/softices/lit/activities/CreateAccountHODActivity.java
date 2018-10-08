package com.softices.lit.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.softices.lit.R;
import com.softices.lit.database.DataBaseHelper;

public class CreateAccountHODActivity extends Activity {
    Button btn_signup_hod;
    Spinner spinner_dept;
    EditText first_name, last_name, email, password, confpass, mobile_no, address, degree, subject;
    private DataBaseHelper hod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_hod);

        hod = new DataBaseHelper(this);
        btn_signup_hod = (Button) findViewById(R.id.btn_signup_hod);
        password = (EditText) findViewById(R.id.hod_password);
        confpass = (EditText) findViewById(R.id.hod_confpass);
        email = (EditText) findViewById(R.id.hod_email);
        first_name = (EditText) findViewById(R.id.hod_firstname);
        last_name = (EditText) findViewById(R.id.hod_lastname);
        subject = (EditText) findViewById(R.id.hod_sub);
        mobile_no = (EditText) findViewById(R.id.hod_mobile);
        address = (EditText) findViewById(R.id.hod_address);
        degree = (EditText) findViewById(R.id.hod_degree);
        spinner_dept = (Spinner) findViewById(R.id.hod_spinner_dept);


        email.setText("d@d.d");
        first_name.setText("akash");
        last_name.setText("patel");
        password.setText("123456");
        confpass.setText("123456");
        subject.setText("java");
        address.setText("Gunjan");
        degree.setText("M.E");
        mobile_no.setText("9712097274");

        btn_signup_hod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Email_Pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

                if (first_name.length() < 1) {
                    Toast.makeText(CreateAccountHODActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (last_name.length() < 1) {
                    Toast.makeText(CreateAccountHODActivity.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 5 && password.getText().toString().contains(" ")) {
                    Toast.makeText(CreateAccountHODActivity.this, "password must be atleast 6", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().equals(confpass.getText().toString())) {
                    Toast.makeText(CreateAccountHODActivity.this, "password must be same", Toast.LENGTH_SHORT).show();
                } else if (!email.getText().toString().matches(Email_Pattern)) {
                    Toast.makeText(CreateAccountHODActivity.this, "Enter Valid Email Address", Toast.LENGTH_LONG).show();
                } else if (degree.length() < 1) {
                    Toast.makeText(CreateAccountHODActivity.this, "ENTER CORRECT DEGREE", Toast.LENGTH_SHORT).show();
                } else if (mobile_no.length() != 10) {
                    Toast.makeText(CreateAccountHODActivity.this, "ENTER CORRECT MOBILE NO.", Toast.LENGTH_SHORT).show();
                } else if (address.length() < 1) {
                    Toast.makeText(CreateAccountHODActivity.this, "ENTER ADDRESS", Toast.LENGTH_SHORT).show();
                } else {
                    insertUser();
                    Intent intent = new Intent(CreateAccountHODActivity.this, LoginActivity.class);
                    intent.putExtra("data", true);
                    startActivity(intent);
                    finishAffinity();
                }


            }
        });
    }

    public long insertUser() {
        try {
            // get writable database as we want to write data
            SQLiteDatabase mDatabase = hod.getReadableDatabase();
            ContentValues values = new ContentValues();
            // `id` and `timestamp` will be inserted automatically.
            // no need to add them
            values.put(DataBaseHelper.HOD_FIRST_NAME, first_name.getText().toString());
            values.put(DataBaseHelper.HOD_LAST_NAME, last_name.getText().toString());
            values.put(DataBaseHelper.HOD_PASSWORD, password.getText().toString());
            values.put(DataBaseHelper.HOD_EMAIL, email.getText().toString());
            values.put(DataBaseHelper.HOD_DEPARTMENT, spinner_dept.getSelectedItem().toString());
            values.put(DataBaseHelper.HOD_DEGREE, degree.getText().toString());
            values.put(DataBaseHelper.HOD_MOBILE_NO, mobile_no.getText().toString());
            values.put(DataBaseHelper.HOD_ADDRESS, address.getText().toString());
            values.put(DataBaseHelper.HOD_SUBJECTS, subject.getText().toString());


            // insert row
            long id = mDatabase.insert(DataBaseHelper.TABLE_HOD, null, values);
            // close db connection
            hod.close();
            // return newly inserted row id
            return id;
        } catch (Exception e) {
            Log.e("mye", "insertUser" + e);
        }
        return 0;
    }
}


