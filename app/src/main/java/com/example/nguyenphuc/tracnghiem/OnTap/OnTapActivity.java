package com.example.nguyenphuc.tracnghiem.OnTap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenphuc.tracnghiem.BoDe.OnTap;
import com.example.nguyenphuc.tracnghiem.R;

import java.util.ArrayList;

public class OnTapActivity extends AppCompatActivity {

    public ArrayList<OnTap> lstOnTap = new ArrayList<>();
    OnTap ot1 = new OnTap("Hiện tại đơn");
    OnTap ot2 = new OnTap("Hiện tại tiếp diễn");
    OnTap ot3 = new OnTap("Hiện tại hoàn thành");
    OnTap ot4 = new OnTap("Quá khứ đơn");
    OnTap ot5 = new OnTap("Quá khứ tiếp diễn");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_tap);
        final ListView list = (ListView) findViewById(R.id.ontap_listview);
        if (lstOnTap.isEmpty()) {
            lstOnTap.add(ot1);
            lstOnTap.add(ot2);
            lstOnTap.add(ot3);
            lstOnTap.add(ot4);
            lstOnTap.add(ot5);
        }
        OnTapAdapter adapter = new OnTapAdapter(this, R.layout.item_ontap, lstOnTap);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        setTitle("Ôn tập câu hỏi");
        return super.onCreateOptionsMenu(menu);
    }
}
