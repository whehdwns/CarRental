package com.example.corn.carrental.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corn.carrental.adapter.BrandModel;
import com.example.corn.carrental.adapter.locationModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class FeedReaderDbHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME= "CarRental.db";
    private static final int DATABASE_VERSION= 1;
    Context context;
    SQLiteDatabase dbw = getWritableDatabase();
    SQLiteDatabase dbr = getReadableDatabase();
    ContentValues contentValues = new ContentValues();
    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }
    public void insertuser (String nametext, String phonetext, String addresstext, String statetext,  int rentaltext, String birthtext){
        String query = "INSERT INTO customer (c_name, c_phonenumber, c_address, c_state,c_rentalid,  c_birthdate) VALUES ('"+nametext+"','"+phonetext+"','"+addresstext+"', '"+statetext+"','"+rentaltext+"','"+birthtext+"')";
        //Cursor cursor = dbw.rawQuery(query, null);
        //dbw.execSQL(query);
        //cursor.moveToFirst();
        /*contentValues.put("c_name", nametext);
        contentValues.put("c_phonenumber", phonetext);
        contentValues.put("c_address", addresstext);
        contentValues.put("c_state", statetext);
        contentValues.put("c_rentalid", rentaltext);
        contentValues.put("c_birthdate", birthtext);
        dbw.insert("customer", null, contentValues);*/
        dbw.execSQL(query);
        //Cursor cursor = dbw.rawQuery()
        return;
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

    public void deltereservation(String name){
        String query = "DELETE FROM reservation WHERE res_id (SELECT res_id FROM reservation, customer WHERE res_customerid = c_id AND c_name = '" + name+"')";
        //Cursor cursor = dbw.rawQuery(query, null);
        dbw.execSQL(query);
        //return cursor;
    }

   public void updaterepickup(String name, String pickupdate){
        String query="UPDATE reservation SET res_pickupdate = '"+pickupdate+"' WHERE res_customerid  IN (SELECT res_customerid FROM reservation, customer WHERE res_customerid = c_id AND c_name = '" + name+"')";
        //Cursor cursor = dbw.rawQuery(query, null);
       dbw.execSQL(query);
        //return cursor;
    }

    public void updatereturn (String name, String returndate){
        String query = "UPDATE rental SET rt_returndate = '"+returndate+"' WHERE rt_id IN  (SELECT rt_id FROM rental, reservation, customer WHERE c_id= res_customerid AND res_rentalid =rt_id AND c_name = '" + name+"')";
       // Cursor cursor = dbw.rawQuery(query, null);
        dbw.execSQL(query);
       // return cursor;
    }

    public List<BrandModel> getvehiclelist(){
        BrandModel model = null;
        List<BrandModel> modelllist = new ArrayList<>();
        String query ="SELECT v_id, v_brand , v_model, v_type, v_productionyear, v_price, v_color, v_mileage FROM vehicle";
        Cursor cursor = dbw.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                model = new BrandModel();
                model.setId(cursor.getInt(cursor.getColumnIndex("v_id")));
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
        String query ="SELECT v_id, v_brand , v_model, v_type, v_productionyear, v_price, v_color, v_mileage FROM vehicle WHERE v_brand LIKE '%"+datafilter+ "%' OR v_type LIKE '%"+datafilter+"%' OR v_price LIKE '%"+datafilter+"%'";
        Cursor cursor = dbw.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                model = new BrandModel();
                model.setId(cursor.getInt(cursor.getColumnIndex("v_id")));
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
        String query = " SELECT DISTINCT c_name, v_brand, v_model, v_price AS vehicle_rent_price , (b_discount) AS discount, rt_rentaldate, rt_returndate, " +
                        "(julianday(rt_returndate) - julianday(rt_rentaldate)) AS rentalday, " +
                        "(v_price *(julianday(rt_returndate) - julianday(rt_rentaldate))*(1-b_discount)) AS price " +
                        "FROM customer, vehicle, rental, billing, reservation " +
                        "WHERE b_customerid = c_id AND c_id= res_customerid AND res_rentalid = rt_id AND rt_vehicleid = v_id AND c_name ='" +name + "'";
        Cursor cursor = dbw.rawQuery(query, null);
        return  cursor;
    }

    public void insertdmv (String name, String income, String ticket, String accident, String trafficviolation, String vehiclereg, String comment){
        String query = "INSERT INTO DMV ( d_customerid, d_income, d_tickets, d_accident, d_trafficviolation, d_vehiclereg, d_comment) VALUES ((SELECT c_id FROM customer WHERE c_name='" +name + "'), + '"+income +"','"+ticket+"', '"+accident+"','"+trafficviolation+"', '"+vehiclereg+ "','"+comment+"')";
       // Cursor cursor = dbw.rawQuery(query, null);
        dbw.execSQL(query);
      //  return cursor;
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
    //Couldn't read row 0, col -1 from CursorWindow.  Make sure the Cursor is initialized correctly before accessing data from it.
    public List<locationModel> getlocationlist() {
        locationModel location= null;
        List<locationModel> locationlist = new ArrayList<>();
        String query = "SELECT l_id, l_address, l_state, l_carsavail, l_phonenumber FROM location";
        Cursor cursor = dbw.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                location = new locationModel();
                location.setId(cursor.getInt(cursor.getColumnIndex("l_id")));
                location.setAddress(cursor.getString(cursor.getColumnIndex("l_address")));
                location.setState(cursor.getString(cursor.getColumnIndex("l_state")));
                location.setCarsavail(cursor.getString(cursor.getColumnIndex("l_carsavail")));
                location.setPhonenumber(cursor.getString(cursor.getColumnIndex("l_phonenumber")));
                locationlist.add(location);
            }while (cursor.moveToNext());
        }
        return locationlist;
    }
    public List<locationModel> getlocationfilter(String datafilter){
        locationModel location= null;
        List<locationModel> locationlist = new ArrayList<>();
        String query ="SELECT l_id, l_address, l_state, l_carsavail, l_phonenumber FROM location WHERE l_state LIKE '%"+datafilter+ "%'";
        Cursor cursor = dbw.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                location = new locationModel();
                location.setId(cursor.getInt(cursor.getColumnIndex("l_id")));
                location.setAddress(cursor.getString(cursor.getColumnIndex("l_address")));
                location.setState(cursor.getString(cursor.getColumnIndex("l_state")));
                location.setCarsavail(cursor.getString(cursor.getColumnIndex("l_carsavail")));
                location.setPhonenumber(cursor.getString(cursor.getColumnIndex("l_phonenumber")));
                locationlist.add(location);
            }while(cursor.moveToNext());
        }
        return locationlist;
    }

   public void insertpickupdata(String name, String pickupdate, int rentalid, int locationid){
        String query = "INSERT INTO reservation (res_customerid, res_reservedate, res_pickupdate, res_rentalid, res_locationid) VALUES ((SELECT c_id FROM customer WHERE  c_name ='"+name+"'),(SELECT DATE('NOW')),'" +pickupdate+"', '"+rentalid+"','"+locationid+"')";
       // Cursor cursor = dbw.rawQuery(query, null);
       dbw.execSQL(query);
       // return cursor;
    }
// NOT WORKING RIGHT NOW ----------------------
    public void insertreturndata(int rentalid, String name, String rentalday, int vehicleid){
        String query = "INSERT INTO rental(rt_id, rt_vehicleid, rt_rentaldate, rt_returndate) VALUES ('"+rentalid+"', "+vehicleid+", (SELECT DATE('NOW')), (SELECT DATE(res_pickupdate, '+"+rentalday+" days') FROM reservation, rental, customer WHERE c_id=res_customerid  AND c_name='"+name+"'))";
       // Cursor cursor =dbw.rawQuery(query, null);
        dbw.execSQL(query);
        //return  cursor;
    }



    public Cursor rentalinfo(String name){
        String query ="SELECT c_name, v_brand, v_model, res_pickupdate, rt_returndate, l_address, l_state FROM customer, vehicle, reservation, rental, location WHERE l_id = res_locationid AND res_rentalid = rt_id  AND rt_vehicleid  = v_id AND res_customerid =  c_id AND  c_name ='" +name+"'";
        Cursor cursor = dbw.rawQuery(query , null);
        return  cursor;
    }

    public String[] cardtype_spinner(){
        String query = "SELECT DISTINCT b_cardtype FROM billing";
        Cursor cursor =dbw.rawQuery(query, null);
        ArrayList<String> spinnerContent = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                String word = cursor.getString(cursor.getColumnIndexOrThrow("b_cardtype"));
                spinnerContent.add(word);
            }while(cursor.moveToNext());
        }
        String[] allSpinner = new String[spinnerContent.size()];
        allSpinner = spinnerContent.toArray(allSpinner);
        return allSpinner;
    }

    public void cardtype(String name, String cardtype){
        String query = "INSERT INTO billing (b_customerid, b_discount, b_cardtype) VALUES((SELECT c_id FROM customer WHERE c_name = '"+name+"'), 0, '"+cardtype+"')";
        //Cursor cursor = dbw.rawQuery(query, null);
        dbw.execSQL(query);
       // return cursor;
    }

}
