package com.agiledev.agiledeveloper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.agiledev.agiledeveloper.utils.PriorityAdapter;
import com.agiledev.agiledeveloper.utils.UserStoryContainer;

public class PriorityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);

        ListView lv = (ListView) findViewById(R.id.priority_userStoryListView);
        PriorityAdapter adapter = new PriorityAdapter(this, UserStoryContainer.getUserStories());
        lv.setAdapter(adapter);
    }
}
