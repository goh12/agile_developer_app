package com.agiledev.agiledeveloper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.services.PlanningPokerService;
import com.agiledev.agiledeveloper.services.PriorityService;
import com.agiledev.agiledeveloper.utils.PlanningPokerAdapter;
import com.agiledev.agiledeveloper.utils.PriorityAdapter;
import com.agiledev.agiledeveloper.utils.ProjectContainer;

public class PriorityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);

        // Náum í Project úr ProjectContainer til þess að uppfæra titilin á "Action bar" fyrir Project Activity
        Project p = ProjectContainer.getProject();
        if(p != null){
            String title = p.getName();
            setTitle(title);
        }
        else setTitle("Project name not found");

        ListView lv = (ListView) findViewById(R.id.priority_userStoryListView);
        PriorityAdapter adapter = new PriorityAdapter(this, ProjectContainer.getUserStories());
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
            PriorityService service = new PriorityService(this);
            service.finalizeEstimates();
        }

        return super.onOptionsItemSelected(item);
    }

    public void estimatesFinalized(boolean success) {
        if (success) {
            Toast.makeText(this, "Priority estimates have been finalized", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Priority estimates could not be finalized", Toast.LENGTH_SHORT).show();
        }
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


    public void priorityEstimateDeleted(boolean success, String msg) {
        if (success) {
            finish();
            startActivity(getIntent());
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
