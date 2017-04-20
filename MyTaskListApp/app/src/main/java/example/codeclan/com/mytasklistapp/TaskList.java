package example.codeclan.com.mytasklistapp;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
        list.add(new Task(1, "Make Bed", "Does what it says on the tin"));
    }

    public ArrayList<Task> getList() {
        return new ArrayList<Task>(list);
    }

}
