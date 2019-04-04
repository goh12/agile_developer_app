package com.agiledev.agiledeveloper;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.services.ProjectService;

public class CreateProjectActivity extends AppCompatActivity {

    ProjectService service = null;

    private Button mCreateButton;
    private Button mBackButton;
    private EditText mProjectName;
    private EditText mProjectToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        this.service = new ProjectService(this);

        mCreateButton = (Button) findViewById(R.id.createButton);
        mBackButton = (Button) findViewById(R.id.backButton);
        mProjectName = (EditText) findViewById(R.id.projectNameInput);
        mProjectToken = (EditText) findViewById(R.id.projectTokenInput);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mProjectName.getText().toString();
                String token = mProjectToken.getText().toString();

                createProject(name, token);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateProjectActivity.super.onBackPressed();
            }
        });

    }

    public void createProject(String name, String token) {
        // kalla project service hér
        service.create(name, token);
    }

    public void projectCreated(Project project) {

        if ( project != null ) {
            Toast.makeText(CreateProjectActivity.this, "Project " + project.getName() + " created.", Toast.LENGTH_LONG).show();
            CreateProjectActivity.super.onBackPressed();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateProjectActivity.this);
            builder.setMessage("Creating project failed")
                    .setNegativeButton("Retry",null)
                    .create()
                    .show();
        }
    }

}
