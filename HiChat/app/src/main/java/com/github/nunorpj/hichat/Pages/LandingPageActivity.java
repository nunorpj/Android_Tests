package com.github.nunorpj.hichat.Pages;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.nunorpj.hichat.R;

public class LandingPageActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT =1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(LandingPageActivity.this, HomePageActivity.class);
                startActivity(homeIntent);
                finish();
            };
        },SPLASH_TIME_OUT);


    }
}
