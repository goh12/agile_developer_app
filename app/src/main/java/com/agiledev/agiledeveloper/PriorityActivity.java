package com.agiledev.agiledeveloper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.agiledev.agiledeveloper.utils.PlanningPokerAdapter;
import com.agiledev.agiledeveloper.utils.PriorityAdapter;
import com.agiledev.agiledeveloper.utils.ProjectContainer;

public class PriorityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);

        ListView lv = (ListView) findViewById(R.id.priority_userStoryListView);
        PriorityAdapter adapter = new PriorityAdapter(this, ProjectContainer.getUserStories());
        lv.setAdapter(adapter);
    }


    /**
     * function responding according to the server response
     * after creating an estimate
     * @param success
     * @param msg
     */
    public void priorityEstimateCreated(boolean success, String msg) {
        finish();
        startActivity(getIntent());
    }
}
