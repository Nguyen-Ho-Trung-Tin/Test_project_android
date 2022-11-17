package com.nguyenhotrungtin.sqlite_exam2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="products.sqlite";
    public static final int DB_VERSION=1;
    public static final String TBL_NAME="Product";
    public static final String COL_ID="Product_ID";
    public static final String COL_NAME= "Product_Name";
    public static final String COL_PRICE="Product_Price";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE IF NOT EXISTS " + TBL_NAME + "( "+ COL_ID+ " INTERGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(50), "+ COL_PRICE+ " REAL"+ " )";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);

    }
    public Cursor getData(String sql){
        SQLiteDatabase db= getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    public void execSql(String sql){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }

    public int getNumbOfRow(){
        Cursor cursor=getData("SELECT * FROM " + TBL_NAME);
        int numofrow=cursor.getCount();
        cursor.close();
        return 0;
    }

    public void createSampleData(){
        if(getNumbOfRow() == 0){
            execSql("INSERT INTO " + TBL_NAME + " VALUES(NULL, 'Heineken',19000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(NULL, 'Tiger',25000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(NULL, 'Saigon',15000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(NULL, 'SSapporo',20000) ");
        }
    }
}
