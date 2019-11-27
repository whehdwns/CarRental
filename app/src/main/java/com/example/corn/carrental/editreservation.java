package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class editreservation extends AppCompatActivity implements View.OnClickListener{
    public static String named = "name";
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editreservation);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        findViewById(R.id.Update).setOnClickListener(this);
        findViewById(R.id.Cancel).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.Update){
           // startActivity(new Intent(editreservation.this, edit_update.class));
            Intent i = new Intent(editreservation.this, edit_update.class);
            i.putExtra(named, name);
            startActivity(i);
        }else if(view.getId()==R.id.Cancel){
          //  startActivity(new Intent(editreservation.this, edit_cancel.class));
            Intent i = new Intent(editreservation.this, edit_cancel.class);
            i.putExtra(named, name);
            startActivity(i);
        }
    }
}
