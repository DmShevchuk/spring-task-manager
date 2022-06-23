package tasks;

import exceptions.IncorrectTaskIdException;
import users.UsersManager;

import java.util.Comparator;
import java.util.List;

/**
 * Класс, отвечающий за хранение и работу с объектами класса {@link Task}
 * */

public class TaskManager {
    private final List<Task> taskCollection;
    private final UsersManager usersManager;

    public TaskManager(List<Task> taskList, UsersManager usersManager) {
        this.taskCollection = taskList;
        this.usersManager = usersManager;
        shareTaskWithUsers();
    }

    public void changeTaskType(int id, TaskType type) throws IncorrectTaskIdException {
        if (!isIdPresent(id)) {
            throw new IncorrectTaskIdException("Task with id=" + id + " is not present!");
        }
        for (Task task : taskCollection) {
            if (id == task.getId()) {
                task.setType(type);
            }
        }
    }

    // Проверка на то, что пользователь взаимодействует с существующей задачей
    private boolean isIdPresent(int id) {
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

    // Добавление задач пользователю
    private void shareTaskWithUsers(){
        for(Task t: taskCollection){
            usersManager.addTaskToUser(t);
        }
    }

    // Вывод задача в консоль
    public String showTask() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : taskCollection) {
            stringBuilder.append(task.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
