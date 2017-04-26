package example.codeclan.com.mytasklistapp;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void newTask(String priority, String name, String description, String date){
        list.add(new Task(priority, name, description, date));
    }

    public ArrayList<Task> getList() {
        return new ArrayList<Task>(list);
    }

}
