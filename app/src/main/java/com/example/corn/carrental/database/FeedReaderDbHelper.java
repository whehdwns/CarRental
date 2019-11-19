package com.example.corn.carrental.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corn.carrental.adapter.BrandModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class FeedReaderDbHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME= "CarRental.db";
    private static final int DATABASE_VERSION= 1;
    Context context;
    SQLiteDatabase dbw = getWritableDatabase();
    SQLiteDatabase dbr = getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    public Cursor insertuser (String nametext, String phonetext, String addresstext, String statetext, String birthtext, String rentaltext){
        String query = "INSERT INTO customer (c_name, c_phonenumber, c_address, c_state, c_rentalid, c_birthdate) VALUES ('"+nametext+"','"+phonetext+"','"+addresstext+"', '"+statetext+"','"+birthtext+"', '"+rentaltext+"')";
        Cursor cursor = dbw.rawQuery(query, null);
        //db.execSQL(query);
        return cursor;
        //TESTING
    }
    public boolean SearchUser(String nametext){
       String query = "SELECT c_name FROM customer WHERE c_name = '"+nametext+"'";
         //String query = "SELECT EXISTS (SELECT c_name FROM customer WHERE c_name = '"+nametext+"')";
        Cursor cursor =dbw.rawQuery(query, null);
        boolean exitdb = (cursor.getCount()>0);
        cursor.close();
        return exitdb;
    }
    public Cursor viewbrand(){
        String query ="SELECT v_brand FROM vehicle GROUP BY v_brand";
        Cursor cursor = dbr.rawQuery(query, null);
        return cursor;
    }

    public Cursor deltereservation(String name){
        String query = "DELETE FROM reservation WHERE res_id (SELECT res_id FROM reservation, customer WHERE res_customerid = c_id AND c_name = '" + name+"')";
        Cursor cursor = dbw.rawQuery(query, null);
        return cursor;
    }

   public Cursor updaterepickup(String name, String pickupdate){
        String query="UPDATE reservation SET'"+pickupdate+"' WHERE res_customerid  IN (SELECT res_customerid FROM reservation, customer WHERE res_customerid = c_id AND c_name = '" + name+"')";
        Cursor cursor = dbw.rawQuery(query, null);
        return cursor;
    }

    public Cursor updatereturn (String name, String returndate){
        String query = "UPDATE rental SET'"+returndate+"' WHERE rt_id IN  (SELECT rt_id FROM rental, customer WHERE rt_id = c_rentalid AND c_name = '" + name+"')";
        Cursor cursor = dbw.rawQuery(query, null);
        return cursor;
    }

    public List<BrandModel> getvehiclelist(){
        BrandModel model = null;
        List<BrandModel> modelllist = new ArrayList<>();
        String query ="SELECT v_brand , v_model, v_type, v_productionyear, v_price, v_color, v_mileage FROM vehicle";
        Cursor cursor = dbw.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                model = new BrandModel();
                //model.setId(cursor.getInt(cursor.getColumnIndex("v_id")));
                model.setBrand(cursor.getString(cursor.getColumnIndex("v_brand")));
                model.setModel(cursor.getString(cursor.getColumnIndex("v_model")));
                model.setType(cursor.getString(cursor.getColumnIndex("v_type")));
                model.setProductionyear(cursor.getString(cursor.getColumnIndex("v_productionyear")));
                model.setPrice(cursor.getString(cursor.getColumnIndex("v_price")));
                model.setColor(cursor.getString(cursor.getColumnIndex("v_color")));
                model.setMile(cursor.getString(cursor.getColumnIndex("v_mileage")));
                modelllist.add(model);
            }while(cursor.moveToNext());
        }
        return modelllist;
    }
    public List<BrandModel> getvehiclefilter(String datafilter){
        BrandModel model = null;
        List<BrandModel> modelllist = new ArrayList<>();
        String query ="SELECT v_brand , v_model, v_type, v_productionyear, v_price, v_color, v_mileage FROM vehicle WHERE v_brand LIKE '%"+datafilter+ "%' OR v_type LIKE '%"+datafilter+"%' OR v_price LIKE '%"+datafilter+"%'";
        Cursor cursor = dbw.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                model = new BrandModel();
                model.setBrand(cursor.getString(cursor.getColumnIndex("v_brand")));
                model.setModel(cursor.getString(cursor.getColumnIndex("v_model")));
                model.setType(cursor.getString(cursor.getColumnIndex("v_type")));
                model.setProductionyear(cursor.getString(cursor.getColumnIndex("v_productionyear")));
                model.setPrice(cursor.getString(cursor.getColumnIndex("v_price")));
                model.setColor(cursor.getString(cursor.getColumnIndex("v_color")));
                model.setMile(cursor.getString(cursor.getColumnIndex("v_mileage")));
                modelllist.add(model);
            }while(cursor.moveToNext());
        }
        return modelllist;
    }
    public Cursor pay(String name){
        String query = " SELECT (v_price *(julianday(rt_returndate) - julianday(rt_rentaldate))*(1-b_discount)) AS price " +
                        "FROM customer, vehicle, rental, billing, reservation " +
                        "WHERE b_customerid = c_id AND c_id= res_customerid AND res_rentalid = rt_id AND rt_vehicleid = v_id AND c_name ='" +name + "'";
        Cursor cursor = dbw.rawQuery(query, null);
        cursor.moveToFirst();
       // int pay  = cursor.getInt(0);
       // return  pay;
        return  cursor;
    }

    public Cursor receipt(String name){
        String query = " SELECT c_name, v_brand, v_model, v_price AS vehicle_rent_price , (b_discount) AS discount, rt_rentaldate, rt_returndate, " +
                        "(julianday(rt_returndate) - julianday(rt_rentaldate)) AS rentalday, " +
                        "(v_price *(julianday(rt_returndate) - julianday(rt_rentaldate))*(1-b_discount)) AS price " +
                        "FROM customer, vehicle, rental, billing, reservation " +
                        "WHERE b_customerid = c_id AND c_id= res_customerid AND res_rentalid = rt_id AND rt_vehicleid = v_id AND c_name ='" +name + "'";
        Cursor cursor = dbw.rawQuery(query, null);
        return  cursor;
    }

    public Cursor insertdmv (String name, String income, String ticket, String accident, String trafficviolation, String vehiclereg, String comment){
        String query = "INSERT INTO DMV ( d_customerid, d_income, d_tickets, d_accident, d_trafficviolation, d_vehiclereg, d_comment) VALUES ((SELECT c_id FROM customer WHERE c_name='" +name + "'), + '"+income +"','"+ticket+"', '"+accident+"','"+trafficviolation+"', '"+vehiclereg+ "','"+comment+"')";
        Cursor cursor = dbw.rawQuery(query, null);
        return cursor;
   }

    public String[] insurance_Spinner(){
        String query  = "SELECT DISTINCT in_name FROM Insurance";
        Cursor cursor =dbw.rawQuery(query, null);
        ArrayList<String> spinnerContent = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                String word = cursor.getString(cursor.getColumnIndexOrThrow("in_name"));
                spinnerContent.add(word);
            }while(cursor.moveToNext());
        }
        String[] allSpinner = new String[spinnerContent.size()];
        allSpinner = spinnerContent.toArray(allSpinner);
        return allSpinner;
    }


}
