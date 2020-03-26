package com.htngu.quanlynhanvien.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.htngu.quanlynhanvien.NhanVien;

import java.util.ArrayList;

public class DBManager {
    private Context context;
    private static SQLHelper sqlHelper;

    public DBManager(Context context) {
        this.context = context;
        sqlHelper = new SQLHelper(context);
    }


    public void addNV(NhanVien nhanVien) {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(sqlHelper.ID, nhanVien.getMaNV());
        values.put(sqlHelper.NAME, nhanVien.getTenNV());
        values.put(sqlHelper.SEX, nhanVien.getGioiTinh());

        db.insert(sqlHelper.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList getAllStudent() {
        ArrayList<NhanVien> sinhVienArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + sqlHelper.TABLE_NAME;

        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            NhanVien song = new NhanVien(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            sinhVienArrayList.add(song);
            cursor.moveToNext();
        }
        return sinhVienArrayList;

    }

    public void deleteNV(String id) {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
//        String query = "DELETE FROM " + sqlHelper.TABLE_NAME + " WHERE "+SQLHelper.ID+" = "+id;
////        db.rawQuery(query, null);
        db.delete(SQLHelper.TABLE_NAME, SQLHelper.ID +" LIKE ?", new String[]{id});

    }

    public boolean checkMaNV(String ma){
        String query = "SELECT "+SQLHelper.ID+" FROM " + sqlHelper.TABLE_NAME;

        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            String x = cursor.getString(0);
            if (ma.equals(x)) return true;
            cursor.moveToNext();
        }
        return false;
    }

}