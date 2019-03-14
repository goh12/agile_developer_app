package com.agiledev.agiledeveloper;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.agiledev.agiledeveloper.utils.PlanningPokerAdapter;
import com.agiledev.agiledeveloper.utils.ProjectContainer;

public class PlanningPokerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_poker);

        ListView lv = (ListView) findViewById(R.id.planning_poker_userStoryListView);
        PlanningPokerAdapter adapter = new PlanningPokerAdapter(this, ProjectContainer.getUserStories());
        lv.setAdapter(adapter);
    }
}
