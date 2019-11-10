package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.newuser).setOnClickListener(this);
        findViewById(R.id.existinguser).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.newuser){
            startActivity(new Intent(MainActivity.this, newuseractivity.class));
        }else if(view.getId()==R.id.existinguser){
            startActivity(new Intent(MainActivity.this, existinguseractivity.class));
        }
    }
}
