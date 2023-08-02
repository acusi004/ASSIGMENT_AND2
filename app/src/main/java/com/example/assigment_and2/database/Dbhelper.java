package com.example.assigment_and2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper( Context context) {
        super(context, "sanpham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String nguoiDung = "CREATE TABLE NGUOIDUNG(USERNAME TEXT PRIMARY KEY," + "PASSWORD TEXT)";
        db.execSQL(nguoiDung);
        String sanPham = "CREATE TABLE SANPHAM(MASP INTEGER PRIMARY KEY AUTOINCREMENT, " + "GIABAN INTEGER, SOLUONG INTEGER, TENSP TEXT)";
        db.execSQL(sanPham);

        // tao bang
        String sp = "INSERT INTO SANPHAM VALUES (1, 3000, 8, 'IPHONE'),"+
                                                "(2, 2000, 9, 'SAMSUNG'),"+
                                                "(3, 2000, 10, 'OPPO')";
        db.execSQL(sp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public  boolean InsertData (String user ,String pass){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("USERNAME", user);
        cv.put("PASSWORD", pass);
        long result = database.insert("NGUOIDUNG", null, cv);
        if (result==-1){
            return false;
        }else{
            return true;
        }

    }

    public boolean checkUser(String user){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT* FROM NGUOIDUNG WHERE USERNAME = ? ", new String[]{user});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkUserPass(String user, String pass){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM NGUOIDUNG WHERE USERNAME = ? AND PASSWORD = ?", new String[]{user, pass});
        if (cursor.getCount()> 0){
            return true;
        }else {
            return false;
        }
    }

}
