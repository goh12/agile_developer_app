package com.agiledev.agiledeveloper;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.datacontrollers.ProjectDataController;
import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.services.ProjectService;

public class LoginActivity extends AppCompatActivity {
    private Button buttonLogin;
    private Button buttonProject;
    private TextView textView;
    private EditText tokenInput;

    private LinearLayout loginLayout;
    private ProgressBar progressBar;

    private ProjectDataController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = (TextView) findViewById(R.id.loginTitle);
        buttonLogin = (Button) findViewById(R.id.loginButton);
        buttonProject = (Button) findViewById(R.id.createProjectButton);
        tokenInput = (EditText) findViewById(R.id.tokenInput);
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        progressBar = (ProgressBar) findViewById(R.id.loginProgressbar);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = tokenInput.getText().toString();
                login(content);
            }
        });

        buttonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProject();
            }
        });

        checkLogin();
    }


    public void login(String token) {
        ProjectService service = new ProjectService(this);
        service.login(token);
    }

    public void createProject() {
        Intent intent = new Intent(getBaseContext(), CreateProjectActivity.class);
        this.startActivity(intent);
    }

    public void afterLogin(boolean success) {
        if (success) {
            textView.setText("Success");
            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_LONG).show();

            // opna project activity
            Intent intent = new Intent(getBaseContext(), ProjectActivity.class);
            this.startActivity(intent);

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Login failed")
                    .setNegativeButton("Retry",null)
                    .create()
                    .show();
        }
    }

    public void isLoggedIn(boolean success) {
        loginLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        if (success) {
            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_LONG).show();

            // opna project activity
            Intent intent = new Intent(getBaseContext(), ProjectActivity.class);
            this.startActivity(intent);
            finish();
        }
    }

    private void checkLogin() {
        Toast.makeText(LoginActivity.this, "Checking on server.", Toast.LENGTH_SHORT).show();
        loginLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        ProjectService service = new ProjectService(this);
        service.checkLogin();
    }

}
