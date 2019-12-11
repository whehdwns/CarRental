package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class edit_cancel extends AppCompatActivity {
    EditText editname;
    Button delete;
    FeedReaderDbHelper dbHelper;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cancel);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        editname = (EditText)findViewById(R.id.name);
        dbHelper = new FeedReaderDbHelper(this);
        delete = (Button) findViewById(R.id.delete);
        deletereservation();
    }

    private void deletereservation() {
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dbHelper.deltereservation(name);
                Toast.makeText(getApplicationContext(), "Successfully Canceled the Reservation", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(edit_cancel.this, MainActivity.class));
            }
        });
    }
}