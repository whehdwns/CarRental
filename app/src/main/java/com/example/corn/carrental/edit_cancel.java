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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cancel);
        editname = (EditText)findViewById(R.id.name);
        dbHelper = new FeedReaderDbHelper(this);
        delete = (Button) findViewById(R.id.delete);
        deletereservation();
    }

    private void deletereservation() {
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               String name = editname.getText().toString();
                dbHelper.deltereservation(name);
                Toast.makeText(getApplicationContext(), "DELETED", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(edit_cancel.this, SubMenu.class));
            }
        });
    }
}
