package com.agiledev.agiledeveloper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agiledev.agiledeveloper.services.ProjectService;

public class CreateProjectActivity extends AppCompatActivity {

    ProjectService service = null;

    private Button createButton;
    private Button backButton;
    private EditText projectName;
    private EditText projectToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        this.service = new ProjectService(this);

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
                CreateProjectActivity.super.onBackPressed();
            }
        });

    }

    public void createProject(String name, String token) {
        // kalla project service hér
        service.save(name, token);
    }

}
