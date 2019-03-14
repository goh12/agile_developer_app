package com.agiledev.agiledeveloper.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.agiledev.agiledeveloper.CreateEstimateFragment;
import com.agiledev.agiledeveloper.R;

import com.agiledev.agiledeveloper.entities.UserStory;

import java.util.List;

public class PriorityAdapter extends ArrayAdapter<UserStory> {

    public PriorityAdapter(Context context, List<UserStory> stories) {
        super(context, 0, stories);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserStory story = (UserStory) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(
                    getContext()).inflate(R.layout.item_userstory_with_estimates, parent, false);
        };

        TextView storyText = (TextView) convertView.findViewById(R.id.estimate_user_story_content);
        storyText.setText(story.getTextContent());

        ImageButton newEstimate = (ImageButton) convertView.findViewById(R.id.estimate_new);

        ListView estimatesListView = convertView.findViewById(R.id.estimate_list_view);
        EstimateAdapter adapter = new EstimateAdapter(getContext(), story.getPriorityEstimates());
        estimatesListView.setAdapter(adapter);


        estimatesListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estimatesListView.getVisibility() == View.GONE) {
                    estimatesListView.setVisibility(View.VISIBLE);
                } else {
                    estimatesListView.setVisibility(View.GONE);
                }

            }
        });

        newEstimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment createFragment = new CreateEstimateFragment();
                FragmentManager manager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.estimate_user_story_container, createFragment);
                transaction.commit();
            }
        });


        return convertView;
    }
}
