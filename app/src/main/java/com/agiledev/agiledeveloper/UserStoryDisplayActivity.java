package com.agiledev.agiledeveloper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.agiledev.agiledeveloper.entities.UserStory;
import com.agiledev.agiledeveloper.services.UserStoryService;

public class UserStoryDisplayActivity extends AppCompatActivity {

    private UserStory story;
    private TextView textContentView;
    private TextView authorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_story_display);

        textContentView = (TextView) findViewById(R.id.display_userstory_textContent);
        authorView = (TextView) findViewById(R.id.display_userstory_author);

        this.story = (UserStory) getIntent().getSerializableExtra("UserStory");

        textContentView.setText(this.story.getTextContent());
        authorView.setText("Author: " + this.story.getAuthor());
    }

    /**
     * Setja upp menu fyrir activity.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_user_story_menu, menu);
        return true;
    }

    /**
     * Setja upp event handlers fyrir edit.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_edit_userstory) {
            Intent intent = new Intent(this, UserStoryEditActivity.class);
            intent.putExtra("isEditing", true);
            intent.putExtra("UserStory", this.story);
            startActivityForResult(intent, 0);
        } else if (id == R.id.action_delete_userstory) {
            UserStoryService service = new UserStoryService(this);
            service.delete(this.story);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Sér um að skoða niðurstöðu fyrir UserStoryEditActivity.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if (data.getBooleanExtra("userStoryUpdated", false)) {
                Intent intent = new Intent();
                intent.putExtra("shouldRefresh", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    /**
     * Sér um event þegar user story er eytt.
     * @param success
     * @param message
     */
    public void userStoryDeleted(boolean success, String message){
        if(success) {
            Intent intent = new Intent();
            intent.putExtra("shouldRefresh", true);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

}
