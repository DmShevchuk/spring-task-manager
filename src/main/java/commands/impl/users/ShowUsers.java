package commands.impl.users;

import commands.Command;
import users.UsersManager;

public class ShowUsers extends Command {
    private final UsersManager usersManager;

    public ShowUsers(UsersManager usersManager){
        super("show_users", "|| show list of users", 0);
        this.usersManager = usersManager;
    }

    @Override
    public String execute() {
        return usersManager.showUsers();
    }
}
