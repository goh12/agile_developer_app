package com.agiledev.agiledeveloper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.entities.UserStory;
import com.agiledev.agiledeveloper.services.UserStoryService;
import com.agiledev.agiledeveloper.utils.ProjectContainer;

public class UserStoryEditActivity extends AppCompatActivity {

    private UserStoryService Service;
    private UserStory UserStory;
    private Button mButton;
    private boolean isEditing;

    private EditText mEditContent;
    private EditText mEditAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_story_edit);

        // Náum í Project úr ProjectContainer til þess að uppfæra titilin á "Action bar" fyrir Project Activity
        Project p = ProjectContainer.getProject();
        if(p != null){
            String title = p.getName();
            setTitle(title);
        }
        else setTitle("Project name not found");

        this.Service = new UserStoryService(this);
        this.isEditing = getIntent().getBooleanExtra("isEditing", false);

        this.mButton = findViewById(R.id.userstory_edit_button);
        this.mEditContent = findViewById(R.id.userstory_edit_textContent);
        this.mEditAuthor = findViewById(R.id.userstory_edit_author);

        if(this.isEditing) {
            setupEditLayout();
        } else {
            setupCreateLayout();
        }

    }

    /**
     * Frumstillir activity ef verið er að edit-a user story.
     */
    private void setupEditLayout() {
        this.UserStory = (UserStory) getIntent().getSerializableExtra("UserStory");

        this.mEditContent.setText(this.UserStory.getTextContent());
        this.mEditAuthor.setText(this.UserStory.getAuthor());

        mButton.setText(R.string.userstory_edit_label_button_edit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditContent.getText().toString();
                String author = mEditAuthor.getText().toString();

                UserStory.setAuthor(author);
                UserStory.setTextContent(content);

                Service.update(UserStory);
            }
        });
    }

    /**
     * Frumstillir activity ef verið er að create-a user story.
     */
    private void setupCreateLayout() {
        mButton.setText(R.string.userstory_edit_label_button_create);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditContent.getText().toString();
                String author = mEditAuthor.getText().toString();

                UserStory us = new UserStory();
                us.setAuthor(author);
                us.setTextContent(content);

                Service.create(us);
            }
        });
    }

    /**
     * Sér um skil þegar búið er til nýtt User Story
     * @param success
     * @param message
     */
    public void userStoryCreated(boolean success, String message) {
        if (success) {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("shouldRefresh", true);
            setResult(RESULT_OK, intent);
            finish();
        } else{
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sér um skil þegar user story er uppfærð
     * @param success
     * @param message
     */
    public void userStoryUpdated(boolean success, String message) {
        if (success) {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("userStoryUpdated", true);
            setResult(RESULT_OK, intent);
            finish();
        } else{
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

}
