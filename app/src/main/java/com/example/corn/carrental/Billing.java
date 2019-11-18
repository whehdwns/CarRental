package com.example.corn.carrental;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class Billing extends AppCompatActivity {
    TextView bill;
    Button display, pay, receipt;
    EditText editname;
    FeedReaderDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        editname  = (EditText)findViewById(R.id.name);
        display= (Button)findViewById(R.id.searchButton);
        receipt= (Button)findViewById(R.id.receipt);
        pay= (Button)findViewById(R.id.pay);
        dbHelper=new FeedReaderDbHelper(this);
        bill = (TextView)findViewById(R.id.bill);
        displaybill();
        showreceipt();
        finishpay();

    }
    private void displaybill() {
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editname.getText().toString().trim();
                Cursor cursor = dbHelper.pay(name);
                String price = cursor.getString(cursor.getColumnIndex("price"));
                bill.setText("Total Price is "+ "$ "+  price);
                //ERROR
                    //dbHelper.pay(name) shows android.database.sqlite.SQLiteCursor@5bb098b
            }
        });
    }

    private void showreceipt() {
        receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editname.getText().toString();
                Cursor cursor = dbHelper.receipt(name);
                if(cursor.getCount()==0){
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext()){
                    buffer.append("Name: "+cursor.getString(0)+ "\n");
                    buffer.append("Vehicle Brand: "+cursor.getString(1)+ "\n");
                    buffer.append("Vehicle Model: "+cursor.getString(2)+ "\n");
                    buffer.append("Vehicle Rent Price: "+cursor.getString(3)+ "\n");
                    buffer.append("Discount: "+cursor.getString(4)+ "\n");
                    buffer.append("Rental Date: "+cursor.getString(5)+ "\n");
                    buffer.append("Return Date: "+cursor.getString(6)+ "\n");
                    buffer.append("Rent Day: "+cursor.getString(7)+ "\n");
                    buffer.append("Total Price: $"+cursor.getString(8)+ "\n");
                }
                showMessage("Receipt",buffer.toString());
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


    private void finishpay() {
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Finished Payment", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Billing.this, MainActivity.class));
            }
        });
    }
}
