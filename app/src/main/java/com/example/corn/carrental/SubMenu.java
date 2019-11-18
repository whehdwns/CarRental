package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubMenu extends AppCompatActivity {
    TextView text;
    Button reserve, search, editreserve, billing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        reserve = (Button)findViewById(R.id.Reserve);
        search = (Button)findViewById(R.id.Search);
        editreserve = (Button)findViewById(R.id.editreserve);
        billing = (Button)findViewById(R.id.billing);
        text = (TextView)findViewById(R.id.welcomeuser);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        text.setText(name);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubMenu.this, Reservation.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubMenu.this, searchvehicle.class));
            }
        });
        editreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubMenu.this, editreservation.class));
                //Intent i = new Intent(SubMenu.this, editreservation.class);
                //i.putExtra("name", name);
                //startActivity(i);
            }
        });
        billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubMenu.this, Billing.class));
            }
        });

    }

}
