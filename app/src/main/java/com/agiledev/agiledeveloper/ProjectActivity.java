package com.agiledev.agiledeveloper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;
import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.entities.UserStory;
import com.agiledev.agiledeveloper.services.UserStoryService;
import com.agiledev.agiledeveloper.utils.UserStoryArrayAdapter;
import com.agiledev.agiledeveloper.utils.ProjectContainer;

import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    private UserStoryService userStoryService;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        this.mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.project_refresh_layout);

        // Náum í Project úr ProjectContainer til þess að uppfæra titilin á "Action bar" fyrir Project Activity
        Project p = ProjectContainer.getProject();
        if(p != null){
            String title = p.getName();
            setTitle(title);
        }
        else setTitle("Project name not found");

        //Búa til lista af User Stories
        this.userStoryService = new UserStoryService(this);
        this.userStoryService.getAll();

        //Setja refresh listener.
        this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getBaseContext(), "Refreshing", Toast.LENGTH_SHORT).show();
                userStoryService.getAll();
            }
        });

        // Floating takki
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(getBaseContext(), UserStoryEditActivity.class);
                intent.putExtra("isEditing", false);
                startActivityForResult(intent, 0);
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

            case R.id.action_info:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.action_planning_poker:
                intent = new Intent(getBaseContext(), PlanningPokerActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.action_estimate:
                intent = new Intent(getBaseContext(), PriorityActivity.class);
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
        this.userStoryService.getAll();
    }

    /**
     * Birtir allar user stories fyrir project
     * @param stories
     */
    public void displayUserStories(List<UserStory> stories) {
        if(stories == null) {
            return;
        }

        ProjectContainer.setUserStories(stories);
        ListView lView = (ListView) findViewById(R.id.userStoryListView);
        UserStoryArrayAdapter adapter = new UserStoryArrayAdapter(this, stories);
        lView.setAdapter(adapter);
        this.mSwipeRefreshLayout.setRefreshing(false);
    }

}
