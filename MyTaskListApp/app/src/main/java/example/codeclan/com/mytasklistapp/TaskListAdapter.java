package example.codeclan.com.mytasklistapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.mytasklistapp.database.DBHandler;

/**
 * Created by user on 19/04/2017.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {

    public TaskListAdapter(Context context, ArrayList<Task> tasks){
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView ==  null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        Task currentTask = getItem(position);

        TextView ranking = (TextView) listItemView.findViewById(R.id.name);
        ranking.setText(currentTask.getName().toString());

        TextView title = (TextView) listItemView.findViewById(R.id.description);
        title.setText(currentTask.getDescription().toString());

        TextView score = (TextView) listItemView.findViewById(R.id.priority);
        score.setText(currentTask.getPriority().toString());

        TextView completed = (TextView) listItemView.findViewById(R.id.completed);
        completed.setText(currentTask.getCompleted().toString());

        listItemView.setTag(currentTask);

        return listItemView;
    }

}