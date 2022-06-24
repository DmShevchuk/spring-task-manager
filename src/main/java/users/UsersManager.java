package users;

import lombok.Getter;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за хранение и работу с объектами класса {@link User}
 * */

public class UsersManager {
    @Getter
    private final List<User> usersList;

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

    public void addUser(User user){
        this.usersList.add(user);
    }

    public void clearCollection(){
        usersList.clear();
    }
}
