package com.partum.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    private CountDownTimer searchCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchCountDown = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {
                Log.d("CURRENT_TIME", "" + l);
            }

            @Override
            public void onFinish() {
               startActivity(new Intent(SplashScreen.this, Authentication.class));
               finish();
            }
        }.start();

    }
}
