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
import android.widget.Toast;

import com.example.corn.carrental.database.FeedReaderDbHelper;

public class newDMV extends AppCompatActivity {
    EditText editname, editincome, editticket, editaccident, edittrafficviolation, editvehicleregisteration, editcomment;
    Button inserts;
    FeedReaderDbHelper dbHelper;
    String name, income, ticket, accident, trafficviolation, vehicleregisteration, comment;
    public static String named = "name";
    int rentalid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dmv);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        rentalid = intent.getExtras().getInt("rt_id");
        //Spinner insurance = (Spinner)findViewById(R.id.insurance);
       // insurance.findViewById(R.id.insurance);
        //insurance = findViewById(R.id.insurance);
        //editname = findViewById(R.id.names);
        editincome= findViewById(R.id.incomes);
        editticket= findViewById(R.id.tickets);
        editaccident= findViewById(R.id.accidents);
        edittrafficviolation= findViewById(R.id.trafficviolations);
        editvehicleregisteration =findViewById(R.id.vehicleregist);
        editcomment = findViewById(R.id.dmvcomment);
        inserts= (Button)findViewById(R.id.insert);
        dbHelper = new FeedReaderDbHelper(this);
       // String [] insurancelist = dbHelper.insurance_Spinner();
       // ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(newDMV.this,android.R.layout.simple_spinner_item, insurancelist);
      //  spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  insurance.setAdapter(spinnerAdapter);
      /*  insurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    */
        insertdmv();
    }
    private void insertdmv() {
        inserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // name = editname.getText().toString();
                income = editincome.getText().toString();
                ticket = editticket.getText().toString();
                accident = editaccident.getText().toString();
                trafficviolation= edittrafficviolation.getText().toString();
                vehicleregisteration = editvehicleregisteration.getText().toString();
                comment = editcomment.getText().toString();
                dbHelper.insertdmv(name, income, ticket,accident, trafficviolation, vehicleregisteration, comment);
                Toast.makeText(getApplicationContext(), "DMV INFO Added", Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(newDMV.this, SubMenu.class));
                Intent i = new Intent(newDMV.this, SubMenu.class);
                i.putExtra("rt_id", rentalid);
                i.putExtra(named, name);
                startActivity(i);
            }
        });
    }
}
