package com.example.nguyenphuc.tracnghiem.BoDe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenphuc.tracnghiem.DatabaseHelper;
import com.example.nguyenphuc.tracnghiem.R;
import com.example.nguyenphuc.tracnghiem.Slide.ScreenSlideActivity;

import java.io.IOException;
import java.util.ArrayList;

public class BoDeActivity extends AppCompatActivity {

    public ArrayList<BoDe> lstBoDe = new ArrayList<>();
    BoDe bd1 = new BoDe("Đề số 1");
    BoDe bd2 = new BoDe("Đề số 2");
    BoDe bd3 = new BoDe("Đề số 3");
    BoDe bd4 = new BoDe("Đề số 4");
    BoDe bd5 = new BoDe("Đề số 5");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        setTitle("Thi theo bộ đề");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de);

        DatabaseHelper db = new DatabaseHelper(this);
        try {
            db.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ListView list = (ListView) findViewById(R.id.bode_listview);
        if (lstBoDe.isEmpty()) {
            lstBoDe.add(bd1);
            lstBoDe.add(bd2);
            lstBoDe.add(bd3);
            lstBoDe.add(bd4);
            lstBoDe.add(bd5);
        }
        BoDeAdapter adapter = new BoDeAdapter(this, R.layout.item_bode, lstBoDe);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BoDeActivity.this, ScreenSlideActivity.class);
                //Gửi mã đề sang ScreenSlideActivity
                Bundle bundle = new Bundle();
                bundle.putInt("ma_de", i + 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
