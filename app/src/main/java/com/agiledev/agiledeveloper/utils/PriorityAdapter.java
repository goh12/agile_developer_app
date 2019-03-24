package com.agiledev.agiledeveloper.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.agiledev.agiledeveloper.R;

import com.agiledev.agiledeveloper.entities.Estimate;
import com.agiledev.agiledeveloper.entities.UserStory;
import com.agiledev.agiledeveloper.services.PriorityService;

import java.util.List;

public class PriorityAdapter extends ArrayAdapter<UserStory> {
    public PriorityAdapter(Context context, List<UserStory> stories) {
        super(context, 0, stories);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PriorityService service = new PriorityService(getContext());
        final UserStory story = (UserStory) getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(
                    getContext()).inflate(R.layout.item_userstory_with_estimates, parent, false);
        };

        TextView storyText = (TextView) convertView.findViewById(R.id.estimate_user_story_content);
        storyText.setText(story.getTextContent());

        ImageButton newEstimate = (ImageButton) convertView.findViewById(R.id.estimate_new);

        ListView estimatesListView = convertView.findViewById(R.id.estimate_list_view);
        final EstimateAdapter adapter = new EstimateAdapter(getContext(), story.getPriorityEstimates());
        estimatesListView.setAdapter(adapter);

        // create estimate objects
        LinearLayout estimateCreateView = (LinearLayout) convertView.findViewById(R.id.estimate_create_view);
        EditText estimateValue = (EditText) convertView.findViewById(R.id.estimate_number);
        EditText estimateExplanation = (EditText) convertView.findViewById(R.id.estimate_explanation);
        Button submitEstimateButton = (Button) convertView.findViewById(R.id.create_estimate_button);



        estimatesListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (estimatesListView.getAdapter().getCount() > 4) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }

                return false;
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estimatesListView.getVisibility() == View.GONE) {
                    estimateCreateView.setVisibility(View.GONE);
                    estimatesListView.setVisibility(View.VISIBLE);
                } else {
                    estimatesListView.setVisibility(View.GONE);
                }

            }
        });

        newEstimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hehe", "onClick: jeje");
                if (estimateCreateView.getVisibility() == View.GONE) {
                    estimatesListView.setVisibility(View.GONE);
                    estimateCreateView.setVisibility(View.VISIBLE);
                } else {
                    estimateCreateView.setVisibility(View.GONE);
                }
            }
        });

        // handler that creates the estimate object and sends it to the service
        submitEstimateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valueString = estimateValue.getText().toString();
                int finalValue = Integer.parseInt(valueString);

                String estimateExplanationValue = estimateExplanation.getText().toString();

                Estimate estimate = new Estimate(finalValue, estimateExplanationValue, story);

                service.create(adapter, story, estimate);
                estimateCreateView.setVisibility(View.GONE);
                estimatesListView.setVisibility(View.VISIBLE);
            }
        });



        return convertView;
    }
}
