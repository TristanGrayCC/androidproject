package example.codeclan.com.mytasklistapp;

public class Task {

    private int id;
    private String priority;
    private String name;
    private String description;
    private String date;
    private Boolean completed;

    public Task() {
    }

    public Task(String priority, String name, String description, String date) {
        this.priority = priority;
        this.name = name;
        this.description = description;
        this.date = date;
        this.completed = false;
    }

    public Task(Integer id, String priority, String name, String description, Boolean completed, String date) {
        this.id = id;
        this.priority = priority;
        this.name = name;
        this.description = description;
        this.date = date;
        this.completed = completed;
    }

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public String getDate() { return date; }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) { this.date = date; }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}