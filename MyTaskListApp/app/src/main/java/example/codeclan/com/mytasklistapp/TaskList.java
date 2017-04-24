package example.codeclan.com.mytasklistapp;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void newTask(int priority, String name, String description, Boolean completed){
        list.add(new Task(priority, name, description, completed));
    }

    public ArrayList<Task> getList() {
        return new ArrayList<Task>(list);
    }

}
