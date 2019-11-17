package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SubMenu extends AppCompatActivity implements View.OnClickListener{
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        findViewById(R.id.Reserve).setOnClickListener(this);
        findViewById(R.id.Search).setOnClickListener(this);
        findViewById(R.id.editreserve).setOnClickListener(this);
        findViewById(R.id.billing).setOnClickListener(this);
        text = (TextView)findViewById(R.id.welcomeuser);
        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        text.setText(str);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Reserve){
            startActivity(new Intent(SubMenu.this, Reservation.class));
        }else if(view.getId() == R.id.Search){
            startActivity(new Intent(SubMenu.this, searchvehicle.class));
        }else if(view.getId() == R.id.editreserve){
            startActivity(new Intent(SubMenu.this, editreservation.class));
        }else if(view.getId() == R.id.billing){
            startActivity(new Intent(SubMenu.this, Billing.class));
        }

    }
}
