package example.codeclan.com.mytasklistapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import example.codeclan.com.mytasklistapp.database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);

        DBHandler dbHandler = new DBHandler(this);
        ArrayList<Task> lists = dbHandler.getAllTasks();

        TaskListAdapter tasksAdapter = new TaskListAdapter(this, lists);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(tasksAdapter);
    }

    public void getTask(View listItem){
        Task task = (Task) listItem.getTag();
        Log.d("Task name: ", task.getName());
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

}
