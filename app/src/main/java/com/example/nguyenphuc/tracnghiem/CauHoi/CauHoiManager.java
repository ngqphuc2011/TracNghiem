package com.example.nguyenphuc.tracnghiem.CauHoi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguyenphuc.tracnghiem.DatabaseHelper;

import java.util.ArrayList;

public class CauHoiManager {
    private DatabaseHelper dbHelper;

    public CauHoiManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<CauHoi> getCauHoi(int subject) {
        ArrayList<CauHoi> lstCauHoi = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tblcauhoi WHERE subject = "+subject+"", null);
        cursor.moveToFirst();
        do {
            CauHoi item;
            item = new CauHoi(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), "");
            lstCauHoi.add(item);
        } while (cursor.moveToNext());
        return lstCauHoi;
    }

    public ArrayList<CauHoi> getCauHoi() {
        ArrayList<CauHoi> lstCauHoi = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tblcauhoi ORDER BY RANDOM() LIMIT 10", null);
        cursor.moveToFirst();
        do {
            CauHoi item;
            item = new CauHoi(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), "");
            lstCauHoi.add(item);
        } while (cursor.moveToNext());
        return lstCauHoi;
    }
}
