package com.example.sportotosuperlig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent ıntent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(ıntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    private Boolean exit = false;

    public void onBackPressed() {
        if (exit) {
            finish();
        } else {

        }
    }
}
