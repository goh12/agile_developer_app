package com.agiledev.agiledeveloper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agiledev.agiledeveloper.datacontrollers.ProjectDataController;
import com.agiledev.agiledeveloper.services.ProjectService;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private Button buttonProject;
    private TextView textView;

    private ProjectDataController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = (TextView) findViewById(R.id.loginTitle);
        buttonLogin = (Button) findViewById(R.id.loginButton);
        buttonProject = (Button) findViewById(R.id.createProjectButton);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProject();
            }
        });

    }


    public void login() {
        ProjectService service = new ProjectService(this);
        service.login();
    }

    public void createProject() {
        Intent intent = new Intent(getBaseContext(), ProjectActivity.class);
        this.startActivity(intent);
    }

    public void setText(String text) {
        textView.setText(text);
    }

}
