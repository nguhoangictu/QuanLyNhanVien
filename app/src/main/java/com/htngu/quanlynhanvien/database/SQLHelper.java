package com.htngu.quanlynhanvien.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {


    private static final String DATABASENAME = "dbname";
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "nvtable";
    public static final String ID = "nvid";
    public static final String NAME = "nvname";
    public static final String SEX = "nvsex";

    public SQLHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = String.format("CREATE TABLE %s ( %s PRIMARY KEY, %s TEXT, %s TEXT)", TABLE_NAME, ID, NAME, SEX);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
