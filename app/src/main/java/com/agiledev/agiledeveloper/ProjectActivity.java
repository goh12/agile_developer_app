package com.agiledev.agiledeveloper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;
import com.agiledev.agiledeveloper.entities.PlanningPokerEstimate;
import com.agiledev.agiledeveloper.entities.PriorityEstimate;
import com.agiledev.agiledeveloper.entities.UserStory;
import com.agiledev.agiledeveloper.services.PlanningPokerService;
import com.agiledev.agiledeveloper.services.UserStoryService;
import com.agiledev.agiledeveloper.services.PriorityService;
import com.agiledev.agiledeveloper.utils.UserStoryArrayAdapter;

import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    private UserStoryService userStoryService;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.project_refresh_layout);

        //Búa til lista af User Stories
        this.userStoryService = new UserStoryService(this);
        this.userStoryService.getAll();

        //Setja refresh listener.
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getBaseContext(), "Refreshing", Toast.LENGTH_SHORT).show();
                userStoryService.getAll();
            }
        });





        //testService.delete(testEstimate);
    }


    /**
     * Setja upp menu fyrir activity.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_menu, menu);
        return true;
    }

    /**
     * Setja upp events fyrir items í menu.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch (id) {

            case R.id.action_log_out:
                Networking.clearCookies();
                intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.action_create_new:
                intent = new Intent(getBaseContext(), UserStoryEditActivity.class);
                intent.putExtra("isEditing", false);
                startActivityForResult(intent, 0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Sér um að birta uppfærðan lista þegar búið er til nýja User Story
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if(data.getBooleanExtra("shouldRefresh", false)) {
                this.userStoryService.getAll();
            }
        }
    }

    /**
     * Birtir allar user stories fyrir project
     * @param stories
     */
    public void displayUserStories(List<UserStory> stories) {
        if(stories == null) {
            return;
        }
        ListView lView = (ListView) findViewById(R.id.userStoryListView);
        UserStoryArrayAdapter adapter = new UserStoryArrayAdapter(this, stories);
        lView.setAdapter(adapter);
        this.swipeRefreshLayout.setRefreshing(false);
    }

}
