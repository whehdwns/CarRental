package com.example.corn.carrental;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class edit_update extends AppCompatActivity{
    EditText editpickup, editreturn;
    Button updatepickup, updatereturn, backtomenu;
    FeedReaderDbHelper dbHelper;
    String name;
    public static String named = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_update);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        editpickup = findViewById(R.id.editpickup);
        editreturn = findViewById(R.id.editreturn);
        updatepickup = (Button)findViewById(R.id.update_pickup);
        updatereturn= (Button)findViewById(R.id.update_return);
        backtomenu = (Button)findViewById(R.id.BackTOMenu);
        dbHelper=new FeedReaderDbHelper(this);
        updatepickupdate();
        updatereturndate();
        backtomenu();
    }
    private void updatepickupdate() {
        updatepickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pickupdate = editpickup.getText().toString();
                dbHelper.updaterepickup(name, pickupdate);
                Toast.makeText(getApplicationContext(), "Updated Pick up date", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(edit_update.this, SubMenu.class));
            }
        });
    }
    private void updatereturndate() {
        editreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String returndate = editreturn.getText().toString();
                dbHelper.updatereturn(name, returndate);
                Toast.makeText(getApplicationContext(), "Updated Return date", Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(edit_update.this, SubMenu.class));
            }
        });
    }

    private void backtomenu() {
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Back To Menu", Toast.LENGTH_SHORT).show();
              //  startActivity(new Intent(edit_update.this, SubMenu.class));
                Intent i = new Intent(edit_update.this, SubMenu.class);
                i.putExtra(named, name);
                startActivity(i);
            }
        });
    }

}