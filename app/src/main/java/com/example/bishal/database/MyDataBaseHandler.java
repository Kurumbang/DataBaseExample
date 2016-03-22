package com.example.bishal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bishal on 3/22/2016.
 */
public class MyDataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTSNAME = "productName";


    public MyDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID  + "  INTEGER PRIMARY KEY," +
                COLUMN_PRODUCTSNAME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS + ";");
        onCreate(db);
    }

    public void addProduct(Products products){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTSNAME, products.getProductname());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();

    }

    public void deleteProduct(String productName){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PRODUCTS + "WHERE " + COLUMN_PRODUCTSNAME + "= " + productName);
        db.delete(TABLE_PRODUCTS, "productName = ? ", new String[]{productName});

    }
    public String dataBaseToString(){
        String query = "SELECT * FROM " + TABLE_PRODUCTS ;

        String dbString = "";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    dbString += c.getString(c.getColumnIndex("productName"));
                    dbString += "\n";
                }while (c.moveToNext());
            }
        }
        c.close();
        db.close();
        return dbString;
    }
}
