package users;

import tasks.Task;

import java.util.List;

/**
 * Класс, отвечающий за хранение и работу с объектами класса {@link UsersManager}
 * */

public class UsersManager {
    private List<User> usersList;

    public UsersManager(List<User> userList) {
        this.usersList = userList;
    }

    public void addTaskToUser(Task task){
        for (User u: usersList){
            if (task.getOwnerId() == u.getId()){
                u.addTask(task);
            }
        }
    }
}
