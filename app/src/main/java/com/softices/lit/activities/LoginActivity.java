package com.softices.lit.activities;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.softices.lit.R;
import com.softices.lit.database.DataBaseHelper;
import static com.softices.lit.database.DataBaseHelper.FACULTY_EMAIL;
import static com.softices.lit.database.DataBaseHelper.FACULTY_PASSWORD;
import static com.softices.lit.database.DataBaseHelper.HOD_EMAIL;
import static com.softices.lit.database.DataBaseHelper.HOD_PASSWORD;
import static com.softices.lit.database.DataBaseHelper.STUDENT_EMAIL;
import static com.softices.lit.database.DataBaseHelper.STUDENT_PASSWORD;
import static com.softices.lit.database.DataBaseHelper.TABLE_FACULTY;
import static com.softices.lit.database.DataBaseHelper.TABLE_HOD;
import static com.softices.lit.database.DataBaseHelper.TABLE_STUDENT;

public class LoginActivity extends AppCompatActivity {


    Button btn_login, btn_creatacc;
    EditText email, password;
    private DataBaseHelper stu;
    RadioGroup rad_grp_select;
    RadioButton rad_student,rad_faculty,rad_hod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        stu = new DataBaseHelper(this);
        stu.getWritableDatabase();
        rad_grp_select = (RadioGroup) findViewById(R.id.rad_grp_select);
        btn_login = (Button) findViewById(R.id.btn_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        rad_student = (RadioButton) findViewById(R.id.rad_student);
        rad_faculty = (RadioButton) findViewById(R.id.rad_faculty);
        rad_hod = (RadioButton) findViewById(R.id.rad_hod);

        email.setText("s@s.s");
        password.setText("123456");


        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Email_Pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";
                if (!email.getText().toString().matches(Email_Pattern)) {
                    Toast.makeText(LoginActivity.this, "Enter Valid Email Address", Toast.LENGTH_LONG).show();
                } else if (password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "Enter Valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean isLogin = false;
                    String userType = "";
                    if (rad_grp_select.getCheckedRadioButtonId() == R.id.rad_student) {
                        isLogin = checkStudent(email.getText().toString(), password.getText().toString());
                        userType = "student";

                    } else if (rad_grp_select.getCheckedRadioButtonId() == R.id.rad_faculty) {
                        isLogin = checkFaculty(email.getText().toString(), password.getText().toString());
                        userType = "faculty";

                    } else if (rad_grp_select.getCheckedRadioButtonId() == R.id.rad_hod) {
                        isLogin = checkHOD(email.getText().toString(), password.getText().toString());
                        userType = "hod";
                    }
                    if (isLogin) {
                        SharedPreferences sharedpreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("isLogin", "true");
                        editor.putString("user_type", userType);
                        editor.putString("Email", email.getText().toString());
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, ButtonActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "Email or password are wrong..", Toast.LENGTH_SHORT).show();
                    }
                    Log.e("PratikTrainee", "Button1 was clicked ");
                }
            }
        });


        btn_creatacc = (Button) findViewById(R.id.btn_creatacc);

        btn_creatacc.setOnClickListener(new

                                                View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        Intent intent = new Intent(LoginActivity.this, SelectionActivity.class);
                                                        startActivity(intent);
                                                        Log.e("LIT", "btn_creatacc was clicked");
                                                    }
                                                });

        TextView txtView = (TextView) findViewById(R.id.Contact_us);
        txtView.setOnClickListener(new

                                                View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                                                        startActivity(intent);
                                                        Log.e("LIT", "btn_creatacc was clicked");
                                                    }
                                                });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean checkStudent(String enrollment, String password) {
        String[] columns = {
                DataBaseHelper.STUDENT_EMAIL
        };
        SQLiteDatabase database = stu.getWritableDatabase();
        String selection = STUDENT_EMAIL + " = ?" + " AND " + STUDENT_PASSWORD + " = ?";
        String[] selectionArgs = {enrollment, password};
        String query = "SELECT Email FROM " + TABLE_STUDENT;
        Cursor cursor = database.rawQuery(query, null);
        cursor = database.query(TABLE_STUDENT,//Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        stu.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;

    }

    public boolean checkFaculty(String email, String password) {
        String[] columns = {
                DataBaseHelper.FACULTY_EMAIL
        };
        SQLiteDatabase database = stu.getWritableDatabase();
        String selection = FACULTY_EMAIL + " = ?" + " AND " + FACULTY_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        String query = "SELECT Email FROM " + TABLE_FACULTY;
        Cursor cursor = database.rawQuery(query, null);
        cursor = database.query(TABLE_FACULTY,//Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        stu.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkHOD(String email, String password) {
        String[] columns = {
                DataBaseHelper.HOD_EMAIL
        };
        SQLiteDatabase database = stu.getWritableDatabase();
        String selection = HOD_EMAIL + " = ?" + " AND " + HOD_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        String query = "SELECT Email FROM " + TABLE_HOD;
        Cursor cursor= database.rawQuery(query, null);
        cursor = database.query(TABLE_HOD,//Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        stu.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;

    }
}

