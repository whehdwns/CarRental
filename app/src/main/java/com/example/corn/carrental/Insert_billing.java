package com.example.corn.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class Insert_billing extends AppCompatActivity {
    int rentalid;
    String name;
    String cardtype;
    FeedReaderDbHelper dbHelper;
    EditText editcardtype;
    Button insert;
    public static String named = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_billing);
        Intent intent = getIntent();
        dbHelper = new FeedReaderDbHelper(this);
        name = intent.getStringExtra("name");
        rentalid = intent.getExtras().getInt("rt_id");
        editcardtype = findViewById(R.id.cardtype);
        insert = (Button)findViewById(R.id.insert);
        /*Spinner cardtype = (Spinner)findViewById(R.id.cardtype);
        String [] cardtypelist =dbHelper.cardtype_spinner();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Insert_billing.this,android.R.layout.simple_spinner_item, cardtypelist);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardtype.setAdapter(spinnerAdapter);
        cardtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              String cardtype = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        */
        insertbilling();
    }

    private void insertbilling() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardtype = editcardtype.getText().toString();
                dbHelper.cardtype(name, cardtype);
                Intent i = new Intent(Insert_billing.this, Billing.class);
                i.putExtra(named, name);
                i.putExtra("rt_id", rentalid);
                startActivity(i);
            }
        });
    }

}
