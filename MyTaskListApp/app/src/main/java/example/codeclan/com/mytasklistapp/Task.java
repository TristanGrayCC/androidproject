package example.codeclan.com.mytasklistapp;

public class Task {

    private int priority;
    private String name;
    private String description;

    public Task(Integer priority, String name, String description) {
        this.priority = priority;
        this.name = name;
        this.description = description;
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
}