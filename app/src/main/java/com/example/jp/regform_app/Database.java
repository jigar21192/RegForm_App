package com.example.jp.regform_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JP on 24-Nov-17.
 */
public class Database extends SQLiteOpenHelper {

    private static final int db_version=1;
    private static final String db_name="RegForm";
    private static final String Table_name="Details";
    private static final String Id="id";
    private static final String Name="name";
    private static final String Address="address";
    private static final String Mobile="mobile";
    private static final String Image="image";



    Database(Context context) {
        super(context,db_name,null,db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String st= " CREATE TABLE " + Table_name + " ( " + Id + " INTEGER PRIMARY KEY, "+ Name + " TEXT, "
                + Address + " TEXT, "+ Mobile + " TEXT," +Image +" BLOB"+")";
        sqLiteDatabase.execSQL(st);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+Table_name);
        onCreate(sqLiteDatabase);

    }

    public int insertdata(Data_Model dm) {
            SQLiteDatabase d=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Name,dm.getName());
        values.put(Address,dm.getAddress());
        values.put(Mobile,dm.getMobile());
        values.put(Image,dm.getImbyte());

       int i=(int) d.insert(Table_name,null,values);
        Log.e("Row",">>>"+i);
        d.close();

        return i;
    }

    public List<Data_Model> getAllDatabase() {
        List<Data_Model>data_models=new ArrayList<Data_Model>();
        String selectquiry = "SELECT * FROM " + Table_name;
        SQLiteDatabase d=this.getReadableDatabase();
        Cursor cursor =d.rawQuery(selectquiry,null);

        if(cursor.getCount()>0) {
            if (cursor.moveToFirst()) {
                do {
                    Data_Model dm = new Data_Model();
                    dm.setId(Integer.parseInt(cursor.getString(0)));
                    dm.setName(cursor.getString(1));
                    dm.setAddress(cursor.getString(2));
                    dm.setMobile(cursor.getString(3));
                    dm.setImbyte(cursor.getBlob(cursor.getColumnIndex(Image)));
                    data_models.add(dm);
                } while (cursor.moveToNext());

            }
        }

        return data_models;
    }

    public void deletdata(int id) {
        SQLiteDatabase d=this.getWritableDatabase();
        d.delete(Table_name,Id+" = ?",new String[]{String.valueOf(id)});
        d.close();
    }

    public int update(Data_Model dm) {
        SQLiteDatabase d=this.getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put(Name,dm.getName());
        v.put(Address,dm.getAddress());
        v.put(Mobile,dm.getMobile());
        v.put(Image,dm.getImbyte());

        return d.update(Table_name,v,Id + " = ?",new String[]{String.valueOf(dm.getId())});

        }
        }
