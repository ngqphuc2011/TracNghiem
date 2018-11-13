package com.example.nguyenphuc.tracnghiem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FlashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        Thread thread = new Thread(wait);
        thread.start();
    }

    Runnable wait = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                Intent intent = new Intent(FlashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
