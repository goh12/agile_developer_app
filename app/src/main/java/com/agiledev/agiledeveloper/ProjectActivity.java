package com.agiledev.agiledeveloper;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.agiledev.agiledeveloper.services.ProjectService;

public class ProjectActivity extends AppCompatActivity {

    ProjectService service = null;

    private Button createButton;
    private Button backButton;
    private TextView textView;
    private EditText projectName;
    private EditText projectToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        this.service = new ProjectService(this);

        textView = (TextView) findViewById(R.id.loginTitle);
        createButton = (Button) findViewById(R.id.createButton);
        backButton = (Button) findViewById(R.id.backButton);
        projectName = (EditText) findViewById(R.id.projectNameInput);
        projectToken = (EditText) findViewById(R.id.projectTokenInput);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = projectName.getText().toString();
                String token = projectToken.getText().toString();

                createProject(name, token);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectActivity.super.onBackPressed();
            }
        });

    }

    public void createProject(String name, String token) {
        // kalla project service hér
        service.save(name, token);
    }

    public void setSaveText(String text) {

        if ( text.equals("Project created") || text.equals("Project already exists") ) {
            textView.setText("Saved");

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ProjectActivity.this);
            builder.setMessage("Saving failed")
                    .setNegativeButton("Retry",null)
                    .create()
                    .show();
        }
    }

    public void setLoginText(String text) {

        if ( text.equals("true")) {
            textView.setText("Logged in");

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ProjectActivity.this);
            builder.setMessage("Log in failed")
                    .setNegativeButton("Retry",null)
                    .create()
                    .show();
        }
    }

}
