package com.github.nunorpj.hichat.Pages;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.nunorpj.hichat.R;

public class LandingPageActivity extends AppCompatActivity {

    private AnimationDrawable bgd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        bgd=(AnimationDrawable)getWindow().getDecorView().getBackground();
        bgd.setEnterFadeDuration(5000);
        bgd.setExitFadeDuration(2000);


    }

    @Override
    protected void onResume() {
        super.onResume();

        bgd.start();
    }
}
