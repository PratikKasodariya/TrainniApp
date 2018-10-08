package com.softices.lit.activities;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.softices.lit.R;

public class SelectionActivity extends AppCompatActivity {
    Button btn_student, btn_faculty, btn_hod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        btn_student = (Button) findViewById(R.id.btn_student);
        btn_faculty = (Button) findViewById(R.id.btn_faculty);
        btn_hod = (Button) findViewById(R.id.btn_hod);

        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, CreateAccountStudentActivity.class);
                startActivity(intent);

            }
        });
        btn_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, CreateAccountFacultiesActivity.class);
                startActivity(intent);


            }
        });
        btn_hod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, CreateAccountHODActivity.class);
                startActivity(intent);

            }
        });

    }
}
