package example.codeclan.com.mytasklistapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import example.codeclan.com.mytasklistapp.database.DBHandler;

public class DetailsActivity extends AppCompatActivity {

    Task task;
    ImageView ranking;
    TextView description;
    TextView name;
    TextView date;
    CheckBox checkbox;
    ImageButton backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ranking = (ImageView) findViewById(R.id.priority);
        description = (TextView) findViewById(R.id.description_details);
        name = (TextView) findViewById(R.id.name_details);
        date = (TextView) findViewById(R.id.date_details);
        checkbox = (CheckBox) findViewById(R.id.checkbox_completed);
        backbutton = (ImageButton) findViewById(R.id.back_button);

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

        if (priority.equals("Urgent") && !task.getCompleted()) {
            ranking.setBackgroundColor(Color.RED);
        }
        if (priority.equals("Soon") && !task.getCompleted()) {
            ranking.setBackgroundColor(Color.YELLOW);
        }
        if (priority.equals("Anytime") && !task.getCompleted()) {
            ranking.setBackgroundColor(Color.GREEN);
        }
        if (task.getCompleted()){
            ranking.setBackgroundColor(Color.WHITE);
        }

        name.setText(task.getName());

        description.setText(task.getDescription());
        date.setText(task.getDate());

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

    public boolean onDeleteButtonClicked(View view){
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.deleteTask(task);

        Intent intent = new Intent(this, TaskListActivity.class);

        startActivity(intent);
        return true;
    }

    public boolean onUpdateButtonClicked(View view){

        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("Task", task.getID());

        startActivity(intent);
        return true;
    }

    public boolean onBackButtonClicked(View view){

        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
        return true;
    }

}
