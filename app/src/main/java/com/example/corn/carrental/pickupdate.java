package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class pickupdate extends AppCompatActivity {
    EditText editname, editpickup;
    Button pickupdate;
    FeedReaderDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickupdate);
        editname = findViewById(R.id.name);
        editpickup = findViewById(R.id.pickupdate);
        pickupdate =(Button)findViewById(R.id.Update);
        dbHelper = new FeedReaderDbHelper(this);
        updatepickupdate();
    }
    private void updatepickupdate() {
        pickupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editname.getText().toString();
                String pickupdate = editpickup.getText().toString();
                dbHelper.updaterepickup(name, pickupdate);
                Toast.makeText(getApplicationContext(), "Updated Pick up date", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(pickupdate.this, SubMenu.class));
            }
        });
    }
}
