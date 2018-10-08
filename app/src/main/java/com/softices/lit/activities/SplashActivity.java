package com.softices.lit.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.softices.lit.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        subscribeToPushService();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedpreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
                final String isLogin = sharedpreferences.getString("isLogin", "");
                if (isLogin.equalsIgnoreCase("true")) {
                    Intent intent = new Intent(SplashActivity.this, ButtonActivity.class);
                    startActivity(intent);
                    finishAffinity();
                } else {

                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();

                }
            }
        }, SPLASH_TIME_OUT);
    }


    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        Log.d("AndroidBash", "Subscribed");
        Toast.makeText(SplashActivity.this, "Subscribed", Toast.LENGTH_SHORT).show();

    FirebaseInstanceId.getInstance().getToken();
    }

}



