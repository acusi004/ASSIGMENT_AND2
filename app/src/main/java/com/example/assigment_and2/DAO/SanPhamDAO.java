package com.example.assigment_and2.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment_and2.database.Dbhelper;
import com.example.assigment_and2.model.sanPham;

import java.util.ArrayList;

public class SanPhamDAO {
    private Context context;
    private Dbhelper dbhelper;

    private ArrayList<sanPham>list;

    private SQLiteDatabase database;

    public SanPhamDAO(Context context){
        dbhelper = new Dbhelper(context);
    }



    public  boolean add(sanPham sp){
        database = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("MASP", sp.getMaSp());
        cv.put("GIABAN", sp.getGiaBan());
        cv.put("SOLUONG", sp.getSoLuong());
        cv.put("TENSP", sp.getTenSp());
        long result =  database.insert("SANPHAM", null, cv);
        return result != 1;
    }

    public boolean update(sanPham sp){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("GIABAN", sp.getGiaBan());
        cv.put("SOLUONG", sp.getSoLuong());
        cv.put("TENSP", sp.getTenSp());
        int check = database.update("SANPHAM", cv, "masp =?", new String[]{String.valueOf(sp.getMaSp())});
        return check != -1;
    }


    public boolean delete(int id) {
        SQLiteDatabase data = dbhelper.getWritableDatabase();
        int row = data.delete("SANPHAM", "masp =?", new String[]{String.valueOf(id)});
        return row != -1;

    }



    // ham lay tat ca du lieu bang
    public ArrayList<sanPham> getALl(){
        ArrayList<sanPham> list = new ArrayList<>();
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        database.beginTransaction();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM SANPHAM ", null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do{
                    list.add(new sanPham(cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3)));
                }while(cursor.moveToNext());
            }
        }catch (Exception e){
            Log.e("ab", e.getMessage());
        }finally {
            database.endTransaction();
        }
        return list;
    }




}
