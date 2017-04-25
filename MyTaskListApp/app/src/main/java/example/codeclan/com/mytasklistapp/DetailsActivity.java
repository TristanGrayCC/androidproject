package example.codeclan.com.mytasklistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import example.codeclan.com.mytasklistapp.database.DBHandler;

public class DetailsActivity extends AppCompatActivity {

    Task task;
    TextView ranking;
    TextView description;
    TextView name;
    TextView completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

         ranking = (TextView) findViewById(R.id.priority);
         description = (TextView) findViewById(R.id.description_details);
         name = (TextView) findViewById(R.id.name_details);

       Intent intent = getIntent();
        String id = intent.getExtras().getString("Task");
        int taskID = Integer.parseInt(id);
        DBHandler dbHandler = new DBHandler(this);
        task = dbHandler.getTask(taskID);

        ranking.setText(task.getPriority().toString());

        name.setText(task.getName());

        description.setText(task.getDescription());

        completed.setText(task.getCompleted().toString());
    }

    public boolean onDeleteButtonClicked(View listItem){
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.deleteTask(task);

        Intent intent = new Intent(this, TaskListActivity.class);

        startActivity(intent);
        return true;
    }

    public boolean onUpdateButtonClicked(View listItem){

        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("Task", task.getID());

        startActivity(intent);
        return true;
    }

    public void onCheckboxClicked(View view) {
        Task task2 = new Task(task.getID(), task.getPriority(), task.getName(), task.getDescription(), true);
        Task task3 = new Task(task.getID(), task.getPriority(), task.getName(), task.getDescription(), true);
        DBHandler dbHandler = new DBHandler(this);

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkbox_completed:
                if (checked)
                    dbHandler.updateTask(task2);
                else
                    dbHandler.updateTask(task3);
                break;
        }
    }

}
