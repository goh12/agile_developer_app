package com.agiledev.agiledeveloper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agiledev.agiledeveloper.datacontroller.ProjectDataController;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    private ProjectDataController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = new ProjectDataController(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

    }


    public void test() {
        try {
            JSONObject ob = new JSONObject();
            ob.put("token", "florg");

            controller.login(ob);
        } catch (JSONException e) {
            Log.e("JSON", "Failed to create json object");
        }
    }

    public void setText(String text) {
        textView.setText(text);
    }

}
