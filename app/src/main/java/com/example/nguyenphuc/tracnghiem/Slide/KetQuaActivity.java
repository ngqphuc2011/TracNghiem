package com.example.nguyenphuc.tracnghiem.Slide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nguyenphuc.tracnghiem.CauHoi.CauHoi;
import com.example.nguyenphuc.tracnghiem.MainActivity;
import com.example.nguyenphuc.tracnghiem.R;

import java.util.ArrayList;

public class KetQuaActivity extends AppCompatActivity {

    ArrayList<CauHoi> arr_CauHoi = new ArrayList<>();
    int num_noAns = 0, num_true = 0, num_false = 0, num_point = 0;
    TextView txtTrue, txtFalse, txtNoAns, txtPoint;

    public void begin() {
        txtTrue = (TextView) findViewById(R.id.txtTrue);
        txtFalse = (TextView) findViewById(R.id.txtFalse);
        txtNoAns = (TextView) findViewById(R.id.txtNoAns);
        txtPoint = (TextView) findViewById(R.id.txtPoint);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        Intent intent = getIntent();
        arr_CauHoi = (ArrayList<CauHoi>) intent.getExtras().getSerializable("arr_CauHoi");
        begin();
        checkResult();
        num_point = num_true * 10;
        txtNoAns.setText(""+num_noAns);
        txtFalse.setText(""+num_false);
        txtTrue.setText(""+num_true);
        txtPoint.setText(""+num_point);

    }

    public void onClick_Back(View view) {
        finish();
    }

    public void onClick_Exit(View view) {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void checkResult() {
        for (int i = 0; i < arr_CauHoi.size(); i++) {
            if (arr_CauHoi.get(i).getTraloi().equals("")) {
                num_noAns++;
            } else if (arr_CauHoi.get(i).getResult().equals(arr_CauHoi.get(i).getTraloi())) {
                num_true++;
            } else num_false++;
        }
    }
}
