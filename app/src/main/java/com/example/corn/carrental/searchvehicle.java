package com.example.corn.carrental;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.corn.carrental.adapter.BrandAdapter;
import com.example.corn.carrental.adapter.BrandModel;
import com.example.corn.carrental.database.FeedReaderDbHelper;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class searchvehicle extends AppCompatActivity {
    //EditText editbrands;
    ListView brandlist;
    private BrandAdapter adapter;
    private List<BrandModel> model;
    BrandModel bm;
    FeedReaderDbHelper dbHelper;
    MaterialSearchBar materialSearchBar;
    public static String named = "name";
    String name;
    int rentalid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchvehicle);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        rentalid = intent.getExtras().getInt("rt_id");
        brandlist = (ListView) findViewById(R.id.brandlist);
        dbHelper=new FeedReaderDbHelper(this);
        model = dbHelper.getvehiclelist();
        materialSearchBar =(MaterialSearchBar)findViewById(R.id.search_bar);
        materialSearchBar.setHint("Search:");
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    brandlist.setAdapter(adapter);
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
        adapter = new BrandAdapter(this, model);
        brandlist.setAdapter(adapter);
        brandlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Intent intent =new Intent(searchvehicle.this, Reservation.class);
                //startActivity(intent);
                /*
                passing vehicle id to next activity
                 */
                bm = (BrandModel) adapter.getItem(i);
               // Intent intent = new Intent(searchvehicle.this, SubMenu.class);
                Intent intent =new Intent(searchvehicle.this, Reservation.class);
                intent.putExtra("v_id", bm.getId());
                intent.putExtra("rt_id", rentalid);
                intent.putExtra(named, name);
                startActivity(intent);
            }
        });

    }
    private void startSearch(String s){
        adapter = new BrandAdapter(this, dbHelper.getvehiclefilter(s));
        brandlist.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.vehicle_filter_menu, menu);
        return true;
    }

}
