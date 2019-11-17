package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class edit_update extends AppCompatActivity implements  View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_update);
        findViewById(R.id.update_pickup).setOnClickListener(this);
        findViewById(R.id.update_return).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.update_pickup){
            startActivity(new Intent(edit_update.this, pickupdate.class));
        }else if(view.getId() == R.id.update_return){
            startActivity(new Intent(edit_update.this, returndate.class));
        }
    }
}
