package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class newuseractivity extends AppCompatActivity {
    EditText editname, editphonenumber, editaddress, editstate, editbirthdate, editrentalid;
    Button insert;
    FeedReaderDbHelper dbHelper;
    String name, phonenumber, address, state, birthdate, rentalid;
    public static String names = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuseractivity);
        editname = findViewById(R.id.name);
        editphonenumber = findViewById(R.id.phonenumber);
        editaddress = findViewById(R.id.address);
        editstate = findViewById(R.id.state);
        editbirthdate = findViewById(R.id.birthdate);
       // editrentalid = findViewById(R.id.rentalid);
        insert = (Button) findViewById(R.id.insert);
        dbHelper = new FeedReaderDbHelper(this);
       // CheckEditTextStatus();
        insertuser();
    }

    private void insertuser() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper.SearchUser(name)) {
                    Toast.makeText(getApplicationContext(), "already exist", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(newuseractivity.this,MainActivity.class));
                } else {
                    name = editname.getText().toString();
                    phonenumber = editphonenumber.getText().toString();
                    address = editaddress.getText().toString();
                    state = editstate.getText().toString();
                    birthdate = editbirthdate.getText().toString();
                   // rentalid = editrentalid.getText().toString();
                    dbHelper.insertuser(name, phonenumber, address, state, birthdate);//, rentalid);
                    Toast.makeText(getApplicationContext(), "User Added", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(newuseractivity.this, newDMV.class));
                //    startActivity(new Intent(newuseractivity.this, SubMenu.class));
                    Intent i = new Intent(newuseractivity.this, newDMV.class);
                    i.putExtra(names, name);
                    startActivity(i);
                }
            }
        });

    }

  /*  public void CheckEditTextStatus(){
        name = editname.getText().toString();
        if(TextUtils.isEmpty(name)){
            EditTextEmptyHold = false ;
        }
        else {
            EditTextEmptyHold = true ;
        }
    }
    */
}
