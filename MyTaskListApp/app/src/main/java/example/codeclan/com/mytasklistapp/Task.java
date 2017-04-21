package example.codeclan.com.mytasklistapp;

public class Task {

    private int id;
    private int priority;
    private String name;
    private String description;

    public Task() {
    }

    public Task(Integer priority, String name, String description) {
        this.priority = priority;
        this.name = name;
        this.description = description;
    }

    public Task(Integer id, Integer priority, String name, String description) {
        this.id = id;
        this.priority = priority;
        this.name = name;
        this.description = description;
    }

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}