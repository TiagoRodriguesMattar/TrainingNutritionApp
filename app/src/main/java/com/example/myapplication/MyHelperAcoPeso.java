package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelperAcoPeso extends SQLiteOpenHelper{
    private Context con;

    public MyHelperAcoPeso(@Nullable Context context) {
        super(context, "DataPeso", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table Peso (xValues INTEGER, yValues INTEGER);";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(long x, int y){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("xValues", x);
        contentValues.put("yValues", y);

        db.insert("Peso", null, contentValues);
    }
}
