package com.agiledev.agiledeveloper;

<<<<<<< Updated upstream
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.agiledev.agiledeveloper.services.ProjectService;
=======
        import android.app.AlertDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.agiledev.agiledeveloper.datacontrollers.UserStoryDataController;
        import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;
        import com.agiledev.agiledeveloper.entities.UserStory;
        import com.agiledev.agiledeveloper.services.ProjectService;
        import com.agiledev.agiledeveloper.services.UserStoryService;
        import com.agiledev.agiledeveloper.utils.UserStoryArrayAdapter;

        import java.util.List;
>>>>>>> Stashed changes

public class ProjectActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
<<<<<<< Updated upstream
=======

        UserStoryService s = new UserStoryService();

        List<UserStory> ls = s.getAll();

        UserStoryArrayAdapter adapter = new UserStoryArrayAdapter(this, ls);

        ListView listView = (ListView) findViewById(R.id.userStoryListView);
        listView.setAdapter(adapter);


        ((Button) findViewById(R.id.logoutbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Networking.clearCookies();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                ProjectActivity.this.startActivity(intent);

                finish();
            }
        });
>>>>>>> Stashed changes
    }



    public void debugToast() {
        Toast.makeText(this, "Clickerooni", Toast.LENGTH_SHORT).show();
    }
}
