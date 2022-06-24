package commands.impl.users;

import commands.Command;
import users.UsersManager;

public class ClearUsers extends Command {
    private final UsersManager usersManager;

    public ClearUsers(UsersManager usersManager) {
        super("clear_users", "|| remove all users from collection", 0);
        this.usersManager = usersManager;
    }

    @Override
    public String execute() {
        usersManager.clearCollection();
        return "Collection was cleared successfully!";
    }
}
