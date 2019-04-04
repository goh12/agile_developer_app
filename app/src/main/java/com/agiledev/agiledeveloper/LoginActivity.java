package com.agiledev.agiledeveloper;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.datacontrollers.ProjectDataController;
import com.agiledev.agiledeveloper.services.ProjectService;

public class LoginActivity extends AppCompatActivity {
    private Button mButtonLogin;
    private Button mButtonProject;
    private TextView mTextView;
    private EditText mTokenInput;

    private LinearLayout mLoginLayout;
    private ProgressBar mProgressBar;

    private ProjectDataController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextView = (TextView) findViewById(R.id.loginTitle);
        mButtonLogin = (Button) findViewById(R.id.loginButton);
        mButtonProject = (Button) findViewById(R.id.createProjectButton);
        mTokenInput = (EditText) findViewById(R.id.tokenInput);
        mLoginLayout = (LinearLayout) findViewById(R.id.login_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.loginProgressbar);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mTokenInput.getText().toString();
                login(content);
            }
        });

        mButtonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProject();
            }
        });

        //checkLogin();
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
            mTextView.setText("Success");
            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_LONG).show();

            // opna project activity
            Intent intent = new Intent(getBaseContext(), ProjectActivity.class);
            this.startActivity(intent);
            finish();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Login failed")
                    .setNegativeButton("Retry",null)
                    .create()
                    .show();
        }
    }

    public void isLoggedIn(boolean success) {
        mLoginLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        if (success) {
            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_LONG).show();

            // opna project activity
            Intent intent = new Intent(getBaseContext(), ProjectActivity.class);
            this.startActivity(intent);
            finish();
        }
    }

    private void checkLogin() {
        mLoginLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        ProjectService service = new ProjectService(this);
        service.checkLogin();
    }

}
