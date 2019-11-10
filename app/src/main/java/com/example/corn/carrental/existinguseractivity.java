package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corn.carrental.databases.FeedReaderDbHelper;

public class existinguseractivity extends AppCompatActivity {
    EditText editname;
    Button search;
    FeedReaderDbHelper dbHelper;
    //String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existinguseractivity);
        editname = (EditText)findViewById(R.id.name);
        search =(Button)findViewById(R.id.search);
        dbHelper=new FeedReaderDbHelper(this);
        searchuser();
    }

    private void searchuser() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editname.getText().toString();
             //  dbHelper.SearchUser(name);
                if(dbHelper.SearchUser(name)){
                    startActivity(new Intent(existinguseractivity.this, SubMenu.class));
                }else{
                    Toast.makeText(getApplicationContext(),"WRONG Name, TRY AGAIN", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}