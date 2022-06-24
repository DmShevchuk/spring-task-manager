package users;

import lombok.Getter;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за хранение и работу с объектами класса {@link User}
 */

public class UsersManager {
    @Getter
    private final List<User> usersList;
    private int freeId = 0;

    public UsersManager(List<User> userList) {
        this.usersList = userList;
        distributeId();
    }

    public void addTaskToUser(Task task) {
        for (User u : usersList) {
            if (task.getOwnerId() == u.getId()) {
                u.addTask(task);
            }
        }
    }

    public User getUserById(int id) {
        for (User user : usersList) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

    public boolean isIdPresent(int id) {
        for (User user : usersList) {
            if (id == user.getId()) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User.UserBuilder user) {
        this.usersList.add(user.id(freeId).build());
    }

    public void clearCollection() {
        usersList.clear();
    }

    private void distributeId() {
        for (User u : usersList) {
            if (u.getId() < freeId) {
                u.setId(freeId);
            }
            freeId = u.getId() + 1;
        }
    }

    public String showUsers() {
        StringBuilder stringBuilder = new StringBuilder();
        for (User u : usersList) {
            stringBuilder.append(u).append("\n");
        }
        return stringBuilder.toString();
    }

    public void removeTaskFromUser(Task task, int userId) {
        if (isIdPresent(userId)) {
            User user = getUserById(userId);
            user.removeTask(task);
        }
    }
}
