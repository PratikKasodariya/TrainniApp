package com.softices.lit.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.softices.lit.R;
import com.softices.lit.WebServicesActivity;

public class ButtonActivity extends AppCompatActivity {

    Button btn_student_corner, btn_faculty_corner, btn_hod_corner, btn_lacture, btn_Services, btn_Share_US, btn_Battery,btn_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        btn_student_corner = (Button) findViewById(R.id.btn_student_corner);
        btn_student_corner.setOnClickListener(new

                                                      View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {
                                                              Intent intent = new Intent(ButtonActivity.this, StudentCornerActivity.class);
                                                              startActivity(intent);
                                                              Log.e("LIT", "btn_creatacc was clicked");
                                                          }
                                                      });
        btn_faculty_corner = (Button) findViewById(R.id.btn_faculty_corner);
        btn_faculty_corner.setOnClickListener(new
                                                      View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {
                                                              Intent intent = new Intent(ButtonActivity.this, FacultyCornerActivity.class);
                                                              startActivity(intent);
                                                          }
                                                      });
        btn_hod_corner = (Button) findViewById(R.id.btn_hod_corner);
        btn_hod_corner.setOnClickListener(new
                                                  View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          Intent intent = new Intent(ButtonActivity.this, HODCornerActivity.class);
                                                          startActivity(intent);
                                                      }
                                                  });
        btn_lacture = (Button) findViewById(R.id.btn_lacture);
        btn_lacture.setOnClickListener(new
                                               View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {
                                                       Intent intent = new Intent(ButtonActivity.this, LacturesActivity.class);
                                                       startActivity(intent);
                                                   }
                                               });
        btn_Services = (Button) findViewById(R.id.btn_Services);
        btn_Services.setOnClickListener(new
                                                View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        Intent intent = new Intent(ButtonActivity.this, ServicesActivity.class);
                                                        startActivity(intent);
                                                    }
                                                });
        btn_Share_US = (Button) findViewById(R.id.btn_Share_us);
        btn_Share_US.setOnClickListener(new
                                                View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        Intent intent = new Intent(ButtonActivity.this, ShareusActivity.class);
                                                        startActivity(intent);
                                                    }
                                                });
        btn_Battery = (Button) findViewById(R.id.btn_Battery);
        btn_Battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonActivity.this, BatteryIndicatorActivity.class);
                startActivity(intent);
            }
        });
        btn_web = (Button) findViewById(R.id.btn_web);
        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonActivity.this, WebServicesActivity.class);
                startActivity(intent);
            }
        });

    }


}
