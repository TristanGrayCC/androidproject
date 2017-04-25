package example.codeclan.com.mytasklistapp;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void newTask(int priority, String name, String description){
        list.add(new Task(priority, name, description));
    }

    public ArrayList<Task> getList() {
        return new ArrayList<Task>(list);
    }

}
