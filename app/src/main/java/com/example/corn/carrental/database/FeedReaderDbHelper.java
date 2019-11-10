package com.example.corn.carrental.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class FeedReaderDbHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME= "CarRental.db";
    private static final int DATABASE_VERSION= 1;
    Context context;
    SQLiteDatabase db = getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    public Cursor insertuser (String nametext, String phonetext, String addresstext, String statetext, String birthtext, String rentaltext){
        String query = "INSERT INTO customer (c_name, c_phonenumber, c_address, c_state, c_rentalid, c_birthdate) VALUES ('"+nametext+"','"+phonetext+"','"+addresstext+"', '"+statetext+"','"+birthtext+"', '"+rentaltext+"')";
        Cursor cursor = db.rawQuery(query, null);
        //db.execSQL(query);
        return cursor;
        //TESTING
    }
    public boolean SearchUser(String nametext){
       String query = "SELECT c_name FROM customer WHERE c_name = '"+nametext+"'";
         //String query = "SELECT EXISTS (SELECT c_name FROM customer WHERE c_name = '"+nametext+"')";
        Cursor cursor =db.rawQuery(query, null);
        boolean exitdb = (cursor.getCount()>0);
        cursor.close();
        return exitdb;
        /*cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
        */
    }


}
