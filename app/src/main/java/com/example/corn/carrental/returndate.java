package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class returndate extends AppCompatActivity {
    EditText editname, editreturn;
    Button returndate;
    FeedReaderDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returndate);
        editname = findViewById(R.id.name);
        editreturn = findViewById(R.id.returndate);
        returndate =(Button)findViewById(R.id.update);
        dbHelper = new FeedReaderDbHelper(this);
        updatereturndate();
    }

    private void updatereturndate() {
        returndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editname.getText().toString();
                String returndate = editreturn.getText().toString();
                dbHelper.updatereturn(name, returndate);
                Toast.makeText(getApplicationContext(), "Updated Return date", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(returndate.this, SubMenu.class));
            }
        });
    }
}
