package com.agiledev.agiledeveloper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.entities.UserStory;
import com.agiledev.agiledeveloper.services.UserStoryService;

public class UserStoryEditActivity extends AppCompatActivity {

    private UserStoryService service;
    private UserStory userStory;
    private Button button;
    private boolean isEditing;

    private EditText editContent;
    private EditText editAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_story_edit);

        this.service = new UserStoryService(this);
        this.isEditing = getIntent().getBooleanExtra("isEditing", false);

        this.button = findViewById(R.id.userstory_edit_button);
        this.editContent = findViewById(R.id.userstory_edit_textContent);
        this.editAuthor = findViewById(R.id.userstory_edit_author);

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
        this.userStory = (UserStory) getIntent().getSerializableExtra("UserStory");

        this.editContent.setText(this.userStory.getTextContent());
        this.editAuthor.setText(this.userStory.getAuthor());

        button.setText(R.string.userstory_edit_label_button_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editContent.getText().toString();
                String author = editAuthor.getText().toString();

                userStory.setAuthor(author);
                userStory.setTextContent(content);

                service.update(userStory);
            }
        });
    }

    /**
     * Frumstillir activity ef verið er að create-a user story.
     */
    private void setupCreateLayout() {
        button.setText(R.string.userstory_edit_label_button_create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editContent.getText().toString();
                String author = editAuthor.getText().toString();

                UserStory us = new UserStory();
                us.setAuthor(author);
                us.setTextContent(content);

                service.create(us);
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
