package users;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String name;
    private final List<Task> taskList = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    @Override
    public String toString() {
        return String.format("id=%d, name=%s", id, name);
    }
}
