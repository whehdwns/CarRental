package com.example.corn.carrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.corn.carrental.adapter.BrandAdapter;
import com.example.corn.carrental.adapter.locationAdapter;
import com.example.corn.carrental.adapter.locationModel;
import com.example.corn.carrental.database.FeedReaderDbHelper;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

public class Reservation extends AppCompatActivity {
    FeedReaderDbHelper dbHelper;
    ListView locationlist;
    private locationAdapter adapter;
    private List<locationModel> location;
    MaterialSearchBar materialSearchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        locationlist = (ListView)findViewById(R.id.locationlist);
        dbHelper=new FeedReaderDbHelper(this);
        location= dbHelper.getlocationlist();
        materialSearchBar =(MaterialSearchBar)findViewById(R.id.search_bar);
        materialSearchBar.setHint("Search for Location by State:");
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    locationlist.setAdapter(adapter);
                }
            }
            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        adapter= new locationAdapter(this, location);
        locationlist.setAdapter(adapter);
    }
    private void startSearch(String s){
        adapter = new locationAdapter(this, dbHelper.getlocationfilter(s));
        locationlist.setAdapter(adapter);
    }
}
