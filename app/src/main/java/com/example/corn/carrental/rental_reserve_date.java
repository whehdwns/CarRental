package com.example.corn.carrental;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class rental_reserve_date extends AppCompatActivity {
    EditText editpickup, editreturnday;
    Button  insertpickupbutton, insertreturnbutton, rentalinfo, backtomenu;
    FeedReaderDbHelper dbHelper;
    String name, locationid, vehicleid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_reserve_date);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        vehicleid = intent.getStringExtra("v_id");
        locationid = intent.getStringExtra("l_id");
        editpickup= (EditText)findViewById(R.id.pickup);
        editreturnday = (EditText)findViewById(R.id.returnday);
        insertpickupbutton = (Button)findViewById(R.id.pickupbutton);
        insertreturnbutton = (Button)findViewById(R.id.renturnbutton);
        rentalinfo = (Button)findViewById(R.id.showrentalinfo);
        backtomenu = (Button)findViewById(R.id.BackToMenu);
        dbHelper=new FeedReaderDbHelper(this);
        insertpickupdata();
        insertreturndata();
        showrentalinforation();
        finishrental();
    }

    private void insertpickupdata() {
        insertpickupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pickupday = editpickup.getText().toString();
               // dbHelper.insertpickupdata (name, pickupday);
            }
        });
    }

    private void insertreturndata() {
        insertreturnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String renturnday = editreturnday.getText().toString();
               // dbHelper.insertreturndata(name, renturnday);

            }
        });
    }
    private void showrentalinforation() {
        rentalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor  = dbHelper.rentalinfo(name);
                if(cursor.getCount()==0){
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext()) {
                    buffer.append("Name: " + cursor.getString(0) + "\n");
                    buffer.append("Vehicle Brand: " + cursor.getString(1) + "\n");
                    buffer.append("Vehicle Model: " + cursor.getString(2) + "\n");
                    buffer.append("Pick up Date: " + cursor.getString(3) + "\n");
                    buffer.append("Return date: " + cursor.getString(4) + "\n");
                    buffer.append("Address: " + cursor.getString(5) + "\n");
                    buffer.append("State: " + cursor.getString(6) + "\n");
                }
                showMessage("Rental Info",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void finishrental() {
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Finished Reservation", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(rental_reserve_date.this, SubMenu.class));
            }
        });

    }


}
