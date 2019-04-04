package com.agiledev.agiledeveloper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.dataparsers.PlanningPokerDataParser;
import com.agiledev.agiledeveloper.services.PlanningPokerService;
import com.agiledev.agiledeveloper.services.UserStoryService;
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


    /**
     * Setja upp menu fyrir activity.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.estimates_menu, menu);
        return true;
    }

    /**
     * Setja upp event handlers fyrir edit.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.finalize_estimate) {
            PlanningPokerService service = new PlanningPokerService(this);
            service.finalizeEstimates();
        }

        return super.onOptionsItemSelected(item);
    }

    public void estimatesFinalized(boolean success) {
        if (success) {
            Toast.makeText(this, "Estimates have been finalized", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Estimates could not be finalized", Toast.LENGTH_SHORT).show();
        }
    }
}
