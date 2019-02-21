package com.agiledev.agiledeveloper;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.services.ProjectService;

import org.w3c.dom.Text;

public class CreateProjectActivity extends AppCompatActivity {

    ProjectService service = null;

    private Button createButton;
    private Button backButton;
    private EditText projectName;
    private EditText projectToken;
    private TextView tempText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        this.service = new ProjectService(this);

        createButton = (Button) findViewById(R.id.createButton);
        backButton = (Button) findViewById(R.id.backButton);
        projectName = (EditText) findViewById(R.id.projectNameInput);
        projectToken = (EditText) findViewById(R.id.projectTokenInput);
        tempText = (TextView) findViewById(R.id.temporaryText);

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

    public void projectCreated(String text) {

        if ( text.equals("Project created") || text.equals("Project already exists") ) {
            Toast.makeText(CreateProjectActivity.this, "Project created", Toast.LENGTH_LONG).show();
            CreateProjectActivity.super.onBackPressed();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateProjectActivity.this);
            builder.setMessage("Saving failed")
                    .setNegativeButton("Retry",null)
                    .create()
                    .show();
        }
    }

    public void setLoginText(String text) {

        if ( text.equals("true")) {
            tempText.setText("Logged in");

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateProjectActivity.this);
            builder.setMessage("Log in failed")
                    .setNegativeButton("Retry",null)
                    .create()
                    .show();
        }
    }

}
