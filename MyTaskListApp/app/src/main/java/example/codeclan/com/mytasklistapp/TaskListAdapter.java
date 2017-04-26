package example.codeclan.com.mytasklistapp;

import android.content.Context;
import android.graphics.Color;
import android.widget.CheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.mytasklistapp.database.DBHandler;

/**
 * Created by user on 19/04/2017.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {

    CheckBox checkbox;

    public TaskListAdapter(Context context, ArrayList<Task> tasks){
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView ==  null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        final Task currentTask = getItem(position);

        TextView ranking = (TextView) listItemView.findViewById(R.id.name);
        ranking.setText(currentTask.getName());

        TextView title = (TextView) listItemView.findViewById(R.id.description);
        title.setText(currentTask.getDescription());

        ImageView score = (ImageView) listItemView.findViewById(R.id.priority);
        String priority = currentTask.getPriority();

        if (priority == "Urgent") {
            score.setBackgroundColor(Color.RED);
        }
        if (priority == "Soon") {
            score.setBackgroundColor(Color.YELLOW);
        }
        if (priority == "Anytime") {
            score.setBackgroundColor(Color.GREEN);
        }

        Boolean isCompleted = currentTask.getCompleted();
        checkbox = (CheckBox) listItemView.findViewById(R.id.checkbox_comp);
        final DBHandler dbHandler = new DBHandler(parent.getContext());

        if (isCompleted) {
            checkbox.setChecked(true);
        } else {
            checkbox.setChecked(false);
        }

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();

                switch (view.getId()) {
                    case R.id.checkbox_comp:
                        if (checked){
                            currentTask.setCompleted(true);
                            dbHandler.updateTask(currentTask);
                        }
                        else {
                            currentTask.setCompleted(false);
                            dbHandler.updateTask(currentTask);
                        }
                }
            }
        });

        listItemView.setTag(currentTask);

        return listItemView;
    }

}