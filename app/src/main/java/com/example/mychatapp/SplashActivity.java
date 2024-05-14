package com.example.mychatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mychatapp.ui.Tools;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Tools.context = getApplicationContext();

    }

    public void girisyapClick(View view) {
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));

    }

    public void kayitolClick(View view) {
        startActivity(new Intent(SplashActivity.this,RegisterActivity.class));


    }
}