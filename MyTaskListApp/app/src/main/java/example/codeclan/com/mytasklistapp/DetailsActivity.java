package example.codeclan.com.mytasklistapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import example.codeclan.com.mytasklistapp.database.DBHandler;

public class DetailsActivity extends AppCompatActivity {

    Task task;
    ImageView ranking;
    TextView description;
    TextView name;
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ranking = (ImageView) findViewById(R.id.priority);
        description = (TextView) findViewById(R.id.description_details);
        name = (TextView) findViewById(R.id.name_details);
        checkbox = (CheckBox) findViewById(R.id.checkbox_completed);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("Task");
        final int taskID = Integer.parseInt(id);
        final DBHandler dbHandler = new DBHandler(this);
        task = dbHandler.getTask(taskID);
        Boolean isCompleted = task.getCompleted();

        if (isCompleted) {
            checkbox.setChecked(true);
        } else {
            checkbox.setChecked(false);
        }

        String priority = task.getPriority();

        if (priority == "Urgent") {
            ranking.setBackgroundColor(Color.RED);
        }
        if (priority == "Soon") {
            ranking.setBackgroundColor(Color.YELLOW);
        }
        if (priority == "Anytime") {
            ranking.setBackgroundColor(Color.GREEN);
        }

        name.setText(task.getName());

        description.setText(task.getDescription());

//      click on checkbox
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();

                switch (view.getId()) {
                    case R.id.checkbox_completed:
                        if (checked){
                            task.setCompleted(true);
                            dbHandler.updateTask(task);
                        }
                        else {
                            task.setCompleted(false);
                            dbHandler.updateTask(task);
                        }
                }
            }
        });
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

}
