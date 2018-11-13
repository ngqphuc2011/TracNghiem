package com.example.nguyenphuc.tracnghiem.Slide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nguyenphuc.tracnghiem.CauHoi.CauHoi;
import com.example.nguyenphuc.tracnghiem.R;

import java.util.ArrayList;

public class TraLoiAdapter extends BaseAdapter {

    ArrayList lstData;
    LayoutInflater inflater;

    public TraLoiAdapter(ArrayList lstData, Context context) {
        this.lstData = lstData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lstData.size();
    }

    @Override
    public Object getItem(int position) {
        return lstData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CauHoi data = (CauHoi) getItem(position);
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_traloi, null);
            holder.numAnswer = (TextView) view.findViewById(R.id.txtNumAnswer);
            holder.yourAnswer = (TextView) view.findViewById(R.id.txtAnswer);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        int i = position + 1; //Vị trí i ứng với câu hỏi i + 1
        holder.numAnswer.setText("Câu " + i + ": ");
        holder.yourAnswer.setText(data.getTraloi());
        return view;
    }

    private static class ViewHolder {
        TextView numAnswer, yourAnswer;

    }
}
