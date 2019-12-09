package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubMenu extends AppCompatActivity {
    TextView text;
    Button reserve, search, editreserve, billing, backtomainmenu;
    public static String named = "name";
    String name;
    int rentalid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        reserve = (Button)findViewById(R.id.Reserve);
        search = (Button)findViewById(R.id.Search);
        editreserve = (Button)findViewById(R.id.editreserve);
        billing = (Button)findViewById(R.id.billing);
        text = (TextView)findViewById(R.id.welcomeuser);
        backtomainmenu = (Button)findViewById(R.id.backtoMenu);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        rentalid = intent.getExtras().getInt("rt_id");
        text.setText(name);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(SubMenu.this, Reservation.class));
               Intent i = new Intent(SubMenu.this, Reservation.class);
               // Intent i = new Intent(SubMenu.this, rental_reserve_date.class);
                i.putExtra("rt_id", rentalid);
                i.putExtra(named, name);
                startActivity(i);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(SubMenu.this, searchvehicle.class));
                Intent i = new Intent(SubMenu.this, searchvehicle.class);
                i.putExtra("rt_id", rentalid);
                i.putExtra(named, name);
                startActivity(i);
            }
        });
        editreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(SubMenu.this, editreservation.class));
                Intent i = new Intent(SubMenu.this, editreservation.class);
                i.putExtra("rt_id", rentalid);
                i.putExtra(named, name);
                startActivity(i);
            }
        });
        billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(SubMenu.this, Billing.class));
                //Intent i = new Intent(SubMenu.this, Billing.class);
                Intent i = new Intent(SubMenu.this, Insert_billing.class);
                i.putExtra("rt_id", rentalid);
                i.putExtra(named, name);
                startActivity(i);
            }
        });
        backtomainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubMenu.this, MainActivity.class));
            }
        });

    }

}
