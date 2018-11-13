package com.example.nguyenphuc.tracnghiem.CaiDat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.nguyenphuc.tracnghiem.MusicService;
import com.example.nguyenphuc.tracnghiem.R;

public class CaiDatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);

        Switch aSwitch = findViewById(R.id.music);
    }

}
