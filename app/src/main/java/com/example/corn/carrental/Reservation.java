package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    locationModel lm;
    MaterialSearchBar materialSearchBar;
    String name;
    int vehicleid;
    int rentalid;
    public static String named = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        vehicleid =intent.getExtras().getInt("v_id");
        rentalid = intent.getExtras().getInt("rt_id");
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
        locationlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*
                passing location id to next activity
                 */
                lm = (locationModel) adapter.getItem(i);
                Intent intent =new Intent(Reservation.this, rental_reserve_date.class);
                intent.putExtra("l_id", lm.getId());
                intent.putExtra("rt_id", rentalid);
                intent.putExtra(named, name);
                intent.putExtra("v_id", vehicleid);
                startActivity(intent);
            }
        });
    }
    private void startSearch(String s){
        adapter = new locationAdapter(this, dbHelper.getlocationfilter(s));
        locationlist.setAdapter(adapter);
    }
}
