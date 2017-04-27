package example.codeclan.com.mytasklistapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.CheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import example.codeclan.com.mytasklistapp.database.DBHandler;

import static android.R.attr.value;

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
        String[] parts = currentTask.getDescription().split(" ");
        if (parts.length < 10) {
            title.setText(currentTask.getDescription());
        }
        if (parts.length > 9) {
            String shortDesc = "";
            for (int str = 0; str < 9; str++) {
                shortDesc += parts[str] + " ";
                String shortDesc2 = shortDesc + "...";
                title.setText(shortDesc2);
            }
        }

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentTask.getDate());

        final TextView score = (TextView) listItemView.findViewById(R.id.priority);
        final String priority = currentTask.getPriority();

        if (priority.equals("Urgent") && !currentTask.getCompleted()) {
            score.setBackgroundColor(Color.RED);
        }
        if (priority.equals("Soon") && !currentTask.getCompleted()) {
            score.setBackgroundColor(Color.YELLOW);
        }
        if (priority.equals("Anytime") && !currentTask.getCompleted()) {
            score.setBackgroundColor(Color.GREEN);
        }
        if (currentTask.getCompleted()){
            score.setBackgroundColor(Color.WHITE);
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
                            score.setBackgroundColor(Color.WHITE);
                        }
                        else {

                            if (priority.equals("Urgent")) {
                                score.setBackgroundColor(Color.RED);
                            }
                            if (priority.equals("Soon")) {
                                score.setBackgroundColor(Color.YELLOW);
                            }
                            if (priority.equals("Anytime")) {
                                score.setBackgroundColor(Color.GREEN);
                            }

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