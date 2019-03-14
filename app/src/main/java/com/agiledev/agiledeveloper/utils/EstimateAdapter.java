package com.agiledev.agiledeveloper.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.agiledev.agiledeveloper.R;
import com.agiledev.agiledeveloper.entities.Estimate;

import org.w3c.dom.Text;

import java.util.List;

public class EstimateAdapter extends ArrayAdapter<Estimate> {

    public EstimateAdapter(Context context, List<Estimate> stories) {
        super(context, 0, stories);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Estimate es = (Estimate) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(
                    getContext()).inflate(R.layout.item_estimate, parent, false);
        }

        TextView estimateView = (TextView) convertView.findViewById(R.id.estimate_estimate);
        TextView explanationView = (TextView) convertView.findViewById(R.id.estimate_explanation);

        estimateView.setText(String.valueOf(es.getEstimate()));
        explanationView.setText(es.getExplanation());
        return convertView;
    }

}
