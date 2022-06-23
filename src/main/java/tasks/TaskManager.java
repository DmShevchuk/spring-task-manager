package tasks;

import exceptions.IncorrectTaskIdException;

import java.util.Comparator;
import java.util.List;

public class TaskManager {
    private final List<Task> taskCollection;

    public TaskManager(List<Task> taskList) {
        this.taskCollection = taskList;
    }

    public void changeTaskType(int id, TaskType type) throws IncorrectTaskIdException {
        if (!isPresent(id)) {
            throw new IncorrectTaskIdException("Task with id=" + id + " is not present!");
        }
        for (Task task : taskCollection) {
            if (id == task.getId()) {
                task.setType(type);
            }
        }
    }

    private boolean isPresent(int id) {
        for (Task task : taskCollection) {
            if (id == task.getId()) {
                return true;
            }
        }
        return false;
    }

    public void sort() {
        taskCollection.sort(Comparator.comparing(Task::getType));
    }

    public void add(Task task) {
        taskCollection.add(task);
    }

    public String showTask() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : taskCollection) {
            stringBuilder.append(task.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
