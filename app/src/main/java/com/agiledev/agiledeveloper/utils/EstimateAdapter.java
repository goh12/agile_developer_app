package com.agiledev.agiledeveloper.utils;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.agiledev.agiledeveloper.R;
import com.agiledev.agiledeveloper.entities.Estimate;
import com.agiledev.agiledeveloper.services.PlanningPokerService;
import com.agiledev.agiledeveloper.services.PriorityService;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.List;

public class EstimateAdapter extends ArrayAdapter<Estimate> {

    public EstimateAdapter(Context context, List<Estimate> stories) {
        super(context, 0, stories);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Estimate es = (Estimate) getItem(position);
        final ArrayAdapter adapter = this;

        if (convertView == null) {
            convertView = LayoutInflater.from(
                    getContext()).inflate(R.layout.item_estimate, parent, false);
        }

        TextView estimateView = (TextView) convertView.findViewById(R.id.estimate_estimate);
        TextView explanationView = (TextView) convertView.findViewById(R.id.estimate_explanation);
        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.estimate_delete);

        estimateView.setText(String.valueOf(es.getEstimate()));
        explanationView.setText(es.getExplanation());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(es.getType() == Estimate.Type.PRIORITY) {
                    PriorityService service = new PriorityService(getContext());
                    service.delete(es, adapter);
                } else if (es.getType() == Estimate.Type.PLANNING_POKER) {
                    Log.e("Hey", "Look at me");
                    PlanningPokerService service = new PlanningPokerService(getContext());
                    service.delete(es, adapter);
                }
            }
        });
        return convertView;
    }

}