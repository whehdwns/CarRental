package com.example.corn.carrental;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.corn.carrental.database.FeedReaderDbHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class searchvehicle extends AppCompatActivity {
    EditText editbrands;
    ListView brandlist;
    FeedReaderDbHelper dbHelper;
    ArrayList<String>listbrand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchvehicle);
        editbrands = (EditText)findViewById(R.id.editbrand);
        brandlist = (ListView)findViewById(R.id.brandlist);
        dbHelper=new FeedReaderDbHelper(this);
        viewbrand();
    }

    private void viewbrand() {
        Cursor cursor = dbHelper.viewbrand();
        while(cursor.moveToNext()){
            listbrand.add(cursor.getString(0));
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listbrand);
            brandlist.setAdapter(listAdapter);
        }


    }

}
