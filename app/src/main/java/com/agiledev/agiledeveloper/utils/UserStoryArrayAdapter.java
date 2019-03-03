package com.agiledev.agiledeveloper.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.agiledev.agiledeveloper.ProjectActivity;
import com.agiledev.agiledeveloper.R;
import com.agiledev.agiledeveloper.UserStoryDisplayActivity;
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

        TextView storyText = (TextView) convertView.findViewById(R.id.user_story_content);
        storyText.setText(story.getTextContent());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), UserStoryDisplayActivity.class);
                i.putExtra("UserStory", story);
                ((ProjectActivity) getContext()).startActivityForResult(i, 0);
            }
        });

        return convertView;
    }
}
