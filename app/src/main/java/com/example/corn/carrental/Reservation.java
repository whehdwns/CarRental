package com.example.corn.carrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.corn.carrental.adapter.locationAdapter;
import com.example.corn.carrental.adapter.locationModel;
import com.example.corn.carrental.database.FeedReaderDbHelper;

import java.util.List;

public class Reservation extends AppCompatActivity {
    FeedReaderDbHelper dbHelper;
    ListView locationlist;
    private locationAdapter adapter;
    private List<locationModel> location;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        locationlist = (ListView)findViewById(R.id.locationlist);
        dbHelper=new FeedReaderDbHelper(this);
        location= dbHelper.getlocationlist();
        adapter= new locationAdapter(this, location);
        locationlist.setAdapter(adapter);
    }
}
