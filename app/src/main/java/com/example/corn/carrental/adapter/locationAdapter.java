package com.example.corn.carrental.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.corn.carrental.R;

import java.util.List;

public class locationAdapter  extends BaseAdapter {
    private List<locationModel> location;
    private Context context;

    public locationAdapter(Context context, List<locationModel> location){
        this.context= context;
        this.location = location;
    }
    @Override
    public int getCount() {
        return location.size();
    }

    @Override
    public Object getItem(int i) {
        return location.get(i);
    }

    @Override
    public long getItemId(int i) {
        return location.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.locationlist, null);
        TextView tvaddress = (TextView)v.findViewById(R.id.address);
        TextView tvstate = (TextView)v.findViewById(R.id.state);
        TextView tvcaravail = (TextView)v.findViewById(R.id.caravail);
        TextView tvphonenumber =(TextView)v.findViewById(R.id.phonenumber);
        tvaddress.setText(location.get(i).getAddress());
        tvstate.setText(location.get(i).getState());
        tvcaravail.setText(location.get(i).getCarsavail());
        tvphonenumber.setText(location.get(i).getPhonenumber());
        return  v;
    }
}