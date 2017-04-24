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

import example.codeclan.com.mytasklistapp.database.DBHandler;

public class NewTask extends AppCompatActivity {

    private Button saveButton;
    EditText priorityToSave;
    EditText nameToSave;
    EditText descriptionToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        saveButton = (Button) findViewById(R.id.save_task_button);
        priorityToSave = (EditText) findViewById(R.id.priority_to_save);
        nameToSave = (EditText) findViewById(R.id.name_to_save);
        descriptionToSave = (EditText) findViewById(R.id.description_to_save);
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

    public void onSaveTaskButtonClicked(View view) {
        String intToSave = priorityToSave.getText().toString();
        int intToSaveInt = Integer.parseInt(intToSave);
        String stringOneToSave = nameToSave.getText().toString();
        String stringTwoToSave = descriptionToSave.getText().toString();

        Task task = new Task(intToSaveInt, stringOneToSave, stringTwoToSave);
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.addTask(task);

        Intent intent = new Intent(this, TaskListActivity.class);
        intent.putExtra("prioritySaved", intToSave);
        intent.putExtra("nameSaved", stringOneToSave);
        intent.putExtra("descriptionSaved", stringTwoToSave);

        startActivity(intent);
    }
}
