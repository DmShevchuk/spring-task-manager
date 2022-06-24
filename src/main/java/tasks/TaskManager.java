package tasks;

import lombok.Getter;
import users.UsersManager;

import java.util.Comparator;
import java.util.List;

/**
 * Класс, отвечающий за хранение и работу с объектами класса {@link Task}
 */

public class TaskManager {
    @Getter
    private final List<Task> taskCollection;
    private final UsersManager usersManager;
    private int freeId = 0;

    public TaskManager(List<Task> taskList, UsersManager usersManager) {
        this.taskCollection = taskList;
        this.usersManager = usersManager;

        shareTaskWithUsers();
    }

    public void changeTask(Task task) {
        for (Task t : taskCollection) {
            if (t.getId() == task.getId()) {
                t.setTitle(task.getTitle());
                t.setDescription(task.getDescription());
                t.setDeadline(task.getDeadline());
                t.setOwnerId(task.getOwnerId());
                t.setType(task.getType());
            }
        }
    }

    // Проверка на то, что пользователь указал существующий id
    public boolean isIdPresent(int id) {
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

    public void add(Task.TaskBuilder taskBuilder) {
        taskCollection.add(taskBuilder.id(freeId).build());
        freeId++;
    }


    /**
     * Метод распределяет задачи по пользователям<br/>
     * А проверяет, что у разных задач нет пересечений по id
     */
    private void shareTaskWithUsers() {
        for (Task t : taskCollection) {

            if (t.getId() < freeId) {
                t.setId(freeId);
            }
            freeId = t.getId() + 1;

            usersManager.addTaskToUser(t);
        }
    }

    // Вывод задачи в консоль
    public String showTask() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : taskCollection) {
            stringBuilder.append(task.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public void clearCollection() {
        this.taskCollection.clear();
    }

    public void deleteById(int id) {
        Task task = null;
        for (Task t : taskCollection) {
            if (id == t.getId()) {
                task = t;
                break;
            }
        }
        taskCollection.remove(task);
    }

    public Task getById(int id) {
        for (Task t : taskCollection) {
            if (id == t.getId()) {
                return t;
            }
        }
        return null;
    }

}
