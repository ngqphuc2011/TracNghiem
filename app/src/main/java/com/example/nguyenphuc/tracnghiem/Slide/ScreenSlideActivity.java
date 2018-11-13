package com.example.nguyenphuc.tracnghiem.Slide;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.nguyenphuc.tracnghiem.CauHoi.CauHoi;
import com.example.nguyenphuc.tracnghiem.CauHoi.CauHoiManager;
import com.example.nguyenphuc.tracnghiem.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 10;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    public int checkAns = 0;

    TextView kiemTra;
    TextView xemDiem;
    //Bộ đếm giờ;
    Counter counter;
    TextView timer;
    //Database
    CauHoiManager cauHoiManager;
    ArrayList<CauHoi> arr_CauHoi;


    Button btn_dong, btn_ket_thuc;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        //Bộ đếm giờ
        counter = new Counter(121*1000, 1000);
        timer = (TextView) findViewById(R.id.timer);
        //Quản lý câu hỏi và lấy câu hỏi từ CSDL
        cauHoiManager = new CauHoiManager(this);
        arr_CauHoi = new ArrayList<>();

        //Lấy mã đề từ BoDeActivity
        Bundle bundle = getIntent().getExtras();
        int i = bundle.getInt("ma_de");
        if (i == 999) {
            arr_CauHoi = cauHoiManager.getCauHoi();
        } else {
            arr_CauHoi = cauHoiManager.getCauHoi(i);
        }

        kiemTra = findViewById(R.id.btn_kiem_tra);
        kiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        xemDiem = findViewById(R.id.btn_xem_diem);
        xemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenSlideActivity.this, KetQuaActivity.class);
                intent.putExtra("arr_CauHoi", arr_CauHoi);
                startActivity(intent);
            }
        });

        counter.start();
    }

    public ArrayList<CauHoi> getArr_CauHoi() {
        return arr_CauHoi;
    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position, checkAns); //checkAns = 1 thì kết thúc
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public void checkAnswer() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_answer_dialog);
        dialog.setTitle("Câu trả lời");
        dialog.setCanceledOnTouchOutside(false);
        btn_dong = dialog.findViewById(R.id.btn_dong);
        btn_ket_thuc = dialog.findViewById(R.id.btn_ket_thuc);


        TraLoiAdapter adapter = new TraLoiAdapter(arr_CauHoi, this);
        GridView gridView = (GridView) dialog.findViewById(R.id.answer_gridview);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Đưa đến vị trí của câu đã chọn
                mPager.setCurrentItem(i);
                dialog.dismiss();
            }
        });

        btn_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_ket_thuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.cancel();
                result();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void result() {
        checkAns = 1;
//        mPagerAdapter.notifyDataSetChanged();
        if (mPager.getCurrentItem() >= 3) mPager.setCurrentItem(mPager.getCurrentItem() - 3);
        else if (mPager.getCurrentItem() <= 3) mPager.setCurrentItem(mPager.getCurrentItem() + 3);
        kiemTra.setVisibility(View.GONE);
        xemDiem.setVisibility(View.VISIBLE);
    }

    //Đồng hồ đếm ngược
    public class Counter extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            timer.setText(countTime); //SetText cho TextView hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            timer.setText("00:00");  //SetText cho TextView hiện thị thời gian.
            result();
        }
    }
}