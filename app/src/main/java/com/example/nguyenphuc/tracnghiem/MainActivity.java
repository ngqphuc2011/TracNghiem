package com.example.nguyenphuc.tracnghiem;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nguyenphuc.tracnghiem.BoDe.BoDeActivity;
import com.example.nguyenphuc.tracnghiem.CaiDat.CaiDatActivity;
import com.example.nguyenphuc.tracnghiem.OnTap.OnTapActivity;
import com.example.nguyenphuc.tracnghiem.Slide.ScreenSlideActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
    }


    @Override
    public void onBackPressed() {
        doUnbindService();
        stopService(new Intent(this, MusicService.class));
    }

    public void onClick_1(View view) {
        Intent intent = new Intent(MainActivity.this, ScreenSlideActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ma_de", 999);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClick_2(View view) {
        Intent intent = new Intent(MainActivity.this, BoDeActivity.class);
        startActivity(intent);
    }

    public void onClick_3(View view) {
    }

    public void onClick_4(View view) {
        Intent intent = new Intent(MainActivity.this, OnTapActivity.class);
        startActivity(intent);
    }

    public void onClick_5(View view) {
        Intent intent = new Intent(MainActivity.this, CaiDatActivity.class);
        startActivity(intent);
    }

    public void onClick_6(View view) {
        showAlertDialog();
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát");
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doUnbindService();
                stopService(new Intent(MainActivity.this, MusicService.class));
                finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class), Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

}
