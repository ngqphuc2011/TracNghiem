package com.example.nguyenphuc.tracnghiem.Slide;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.nguyenphuc.tracnghiem.CauHoi.CauHoi;
import com.example.nguyenphuc.tracnghiem.R;

import java.util.ArrayList;


public class ScreenSlidePageFragment extends Fragment {
    public static final String C_PAGE = "page";
    public static final String C_CHECK_ANSWER = "checkAnswer";

    ArrayList<CauHoi> arr_CauHoi;
    public int mPageNumber; //Vị trí trang hiện tại
    public int checkAns; //Biến kiểm tra

    TextView txtNum, txtQuestion;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        txtNum = (TextView) rootView.findViewById(R.id.txtNum);
        txtQuestion = (TextView) rootView.findViewById(R.id.txtQuestion);
        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_CauHoi = new ArrayList<>();
        ScreenSlideActivity screenSlideActivity = (ScreenSlideActivity) getActivity();
        arr_CauHoi = screenSlideActivity.getArr_CauHoi();
        mPageNumber = getArguments().getInt(C_PAGE);
        checkAns = getArguments().getInt(C_CHECK_ANSWER);
    }

    public static ScreenSlidePageFragment create(int pageNumber, int checkAnswer) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(C_PAGE, pageNumber);
        bundle.putInt(C_CHECK_ANSWER, checkAnswer);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtNum.setText("Câu " + (mPageNumber + 1));
        txtQuestion.setText(arr_CauHoi.get(mPageNumber).getQuestion());
        radA.setText(arr_CauHoi.get(mPageNumber).getAns_a());
        radB.setText(arr_CauHoi.get(mPageNumber).getAns_b());
        radC.setText(arr_CauHoi.get(mPageNumber).getAns_c());
        radD.setText(arr_CauHoi.get(mPageNumber).getAns_d());

        if (checkAns != 0) {
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                arr_CauHoi.get(mPageNumber).choiceID = i;
                arr_CauHoi.get(mPageNumber).setTraloi(getChoiceFromID(i));

            }
        });
    }

    //Lấy vị trí của câu hỏi
    public CauHoi getItem(int position) {
        return arr_CauHoi.get(position);
    }

    //Lấy vị trí của RadioGroup chuyển thành đáp án A B C D
    public String getChoiceFromID(int ID) {
        if (ID == R.id.radA) {
            return "A";
        } else if (ID == R.id.radB) {
            return "B";
        } else if (ID == R.id.radC) {
            return "C";
        } else if (ID == R.id.radD) {
            return "D";
        } else return "";
    }

    //Kiểm tra câu đúng, nếu đúng đổi màu background RadioButton
    private void getCheckAns(String ans) {
        if (ans.equals("A")) {
            radA.setTextColor(Color.RED);
        } else if (ans.equals("B")) {
            radB.setTextColor(Color.RED);
        } else if (ans.equals("C")) {
            radC.setTextColor(Color.RED);
        } else if (ans.equals("D")) {
            radD.setTextColor(Color.RED);
        }
    }
}
