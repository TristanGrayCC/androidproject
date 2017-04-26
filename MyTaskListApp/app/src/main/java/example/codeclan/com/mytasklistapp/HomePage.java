package example.codeclan.com.mytasklistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private Button launchButton;
    private Button addButton;
    EditText textToSave;
    Button saveButton;
    TextView savedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        launchButton = (Button) findViewById(R.id.launch_button);
        addButton = (Button) findViewById(R.id.add_button);
        textToSave = (EditText)findViewById(R.id.text_to_save);
        saveButton = (Button)findViewById(R.id.save_button);
        savedText = (TextView)findViewById(R.id.saved_text);
        savedText.setVisibility(View.INVISIBLE);
        launchButton.setVisibility(View.INVISIBLE);
        addButton.setVisibility(View.INVISIBLE);

        String returnedText = SavedTextPreferenceManager.getStoredText(this);

        if (returnedText != null){
            saveButton.setVisibility(View.INVISIBLE);
            textToSave.setVisibility(View.INVISIBLE);
            savedText.setVisibility(View.VISIBLE);
            savedText.setText("Welcome "+returnedText+"!");
            launchButton.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.VISIBLE);
        }
    }

        public void onLaunchButtonClicked(View button) {
            Intent intent = new Intent(HomePage.this, TaskListActivity.class);
            startActivity(intent);
        }

        public void onAddButtonClicked(View button) {
            Intent intent = new Intent(HomePage.this, NewTask.class);
            startActivity(intent);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this, HomePage.class);
                this.startActivity(intent);
                break;
            case R.id.list:
                Intent intent2 = new Intent(this, TaskListActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.newTask:
                Intent intent3 = new Intent(this, NewTask.class);
                this.startActivity(intent3);
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void onSaveButtonClicked(View view) {
        String stringToSave = textToSave.getText().toString();
        Log.d("PersistenceExample:", "Save Button Clicked!");
        Log.d("PersistenceExample:", "The text to save is: '" + textToSave + "'");
        saveButton.setVisibility(View.INVISIBLE);
        textToSave.setVisibility(View.INVISIBLE);
        savedText.setVisibility(View.VISIBLE);
        savedText.setText(stringToSave);
        launchButton.setVisibility(View.VISIBLE);
        addButton.setVisibility(View.VISIBLE);

        SavedTextPreferenceManager.setStoredText(this, stringToSave);
    }

}
