package com.agiledev.agiledeveloper.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.agiledev.agiledeveloper.ProjectActivity;
import com.agiledev.agiledeveloper.R;
import com.agiledev.agiledeveloper.entities.UserStory;

import java.util.List;

public class UserStoryArrayAdapter extends ArrayAdapter<UserStory> {

    public UserStoryArrayAdapter(Context context, List<UserStory> stories) {
        super(context, 0, stories);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UserStory story = (UserStory) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_userstory, parent, false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectActivity activity = (ProjectActivity) v.getContext();
                activity.debugToast();
            }
        });

        return convertView;
    }
}
