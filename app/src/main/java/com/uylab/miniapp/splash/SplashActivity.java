package com.uylab.miniapp.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.uylab.miniapp.R;
import com.uylab.miniapp.product.ProductActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash );


        CountDownTimer countDownTimer =new CountDownTimer(2000,500) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();

    }
}
