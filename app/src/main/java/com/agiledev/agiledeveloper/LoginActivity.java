package com.agiledev.agiledeveloper;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.agiledev.agiledeveloper.datacontrollers.ProjectDataController;
import com.agiledev.agiledeveloper.services.ProjectService;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private Button buttonProject;
    private TextView textView;
    private EditText tokenInput;

    private ProjectDataController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = (TextView) findViewById(R.id.loginTitle);
        buttonLogin = (Button) findViewById(R.id.loginButton);
        buttonProject = (Button) findViewById(R.id.createProjectButton);
        tokenInput = (EditText) findViewById(R.id.tokenInput);

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

    }


    public void login(String token) {
        ProjectService service = new ProjectService(this);
        service.login(token);
    }

    public void createProject() {
        Intent intent = new Intent(getBaseContext(), ProjectActivity.class);
        this.startActivity(intent);
    }

    public void setText(String text) {

        if (text == "success") {
            textView.setText(text);

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Login failed")
                    .setNegativeButton("Retry",null)
                    .create()
                    .show();
        }
    }

}
