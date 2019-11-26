package com.example.corn.carrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class rental_reserve_date extends AppCompatActivity {
    EditText editpickup, editrentalday;
    Button pickupbutton, rentaldaybutton, rentalinfo, backtomenu;
    FeedReaderDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_reserve_date);
        editpickup= (EditText)findViewById(R.id.pickup);
        editrentalday = (EditText)findViewById(R.id.rentalday);
        pickupbutton = (Button)findViewById(R.id.pickupbutton);
        rentaldaybutton = (Button)findViewById(R.id.rentaldaybutton);
        rentalinfo = (Button)findViewById(R.id.showrentalinfo);
        backtomenu = (Button)findViewById(R.id.BackToMenu);
        dbHelper=new FeedReaderDbHelper(this);
    }
}
