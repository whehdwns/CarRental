package com.example.corn.carrental.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.corn.carrental.R;

import java.util.List;

public class BrandAdapter extends BaseAdapter {
    private Context context;
    private List<BrandModel> model;
    public BrandAdapter (Context context, List<BrandModel> model){
        this.context= context;
        this.model = model;
    }
    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int i) {
        return model.get(i);
    }
    @Override
    public long getItemId(int i) {
        return model.get(i).getId();
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.brandlist, null);
        TextView tvbrand = (TextView)v.findViewById(R.id.vehiclebrand);
        TextView tvmodel = (TextView)v.findViewById(R.id.vehiclemodel);
        TextView tvtype = (TextView)v.findViewById(R.id.vehicletype);
        TextView tvyear = (TextView)v.findViewById(R.id.vehicleyear);
        TextView tvprice = (TextView)v.findViewById(R.id.vehicleprice);
        TextView tvcolor = (TextView)v.findViewById(R.id.vehiclecolor);
        TextView tvmile = (TextView)v.findViewById(R.id.vehiclemile);
        tvbrand.setText(model.get(i).getBrand());
        tvmodel.setText(model.get(i).getModel());
        tvtype.setText(model.get(i).getType());
        tvyear.setText(model.get(i).getProductionyear());
        tvprice.setText(model.get(i).getPrice());
        tvcolor.setText(model.get(i).getColor());
        tvmile.setText(model.get(i).getMile());
        return v;
    }
}