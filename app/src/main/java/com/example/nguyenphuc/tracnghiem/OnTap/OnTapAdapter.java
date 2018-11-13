package com.example.nguyenphuc.tracnghiem.OnTap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenphuc.tracnghiem.BoDe.OnTap;
import com.example.nguyenphuc.tracnghiem.R;

import java.util.ArrayList;

public class OnTapAdapter extends ArrayAdapter<OnTap> {
    Context mContext;
    int mLayoutItem;
    ArrayList<OnTap> mList;

    public OnTapAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OnTap> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mLayoutItem = resource;
        this.mList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutItem, null);
        }
        OnTap bd = mList.get(position);
        TextView itemTitle = (TextView) row.findViewById(R.id.bode_title);

        itemTitle.setText(bd.getTitle());
        return row;
    }
}