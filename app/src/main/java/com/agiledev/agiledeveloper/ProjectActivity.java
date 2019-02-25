package com.agiledev.agiledeveloper;

        import android.app.AlertDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;
        import com.agiledev.agiledeveloper.services.ProjectService;

public class ProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        ((Button) findViewById(R.id.logoutbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Networking.clearCookies();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                ProjectActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}
