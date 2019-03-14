package com.agiledev.agiledeveloper.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.agiledev.agiledeveloper.R;
import com.agiledev.agiledeveloper.entities.Estimate;

import java.util.List;

public class EstimateAdapter extends ArrayAdapter<Estimate> {

    public EstimateAdapter(Context context, List<Estimate> stories) {
        super(context, 0, stories);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(
                    getContext()).inflate(R.layout.item_estimate, parent, false);
        }

        return convertView;
    }
}
