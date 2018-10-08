package com.softices.lit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.softices.lit.activities.ButtonActivity;
import com.softices.lit.activities.StudentCornerActivity;

import org.json.JSONObject;

public class WebServicesActivity extends AppCompatActivity {
    Button btn_get, btn_put, btn_post, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_services);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_get.setOnClickListener(new

                                           View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   Intent intent = new Intent(WebServicesActivity.this, GetActivity.class);
                                                   startActivity(intent);
                                                   Log.e("LIT", "btn_creatacc was clicked");
                                               }
                                           });
        btn_put = (Button) findViewById(R.id.btn_put);
        btn_put.setOnClickListener(new

                                           View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   Intent intent = new Intent(WebServicesActivity.this, PutActivity.class);
                                                   startActivity(intent);
                                                   Log.e("LIT", "btn_creatacc was clicked");
                                               }
                                           });
        btn_post = (Button) findViewById(R.id.btn_post);
        btn_post.setOnClickListener(new

                                            View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(WebServicesActivity.this, PostActivity.class);
                                                    startActivity(intent);
                                                    Log.e("LIT", "btn_creatacc was clicked");
                                                }
                                            });
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new

                                              View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View view) {
                                                      Intent intent = new Intent(WebServicesActivity.this, DeleteActivity.class);
                                                      startActivity(intent);
                                                      Log.e("LIT", "btn_creatacc was clicked");
                                                  }
                                              });


    }
}