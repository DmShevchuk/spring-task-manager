package commands.impl.users;

import commands.Command;
import users.UserFactory;
import users.UsersManager;
import utils.CommandLine;

public class AddUser extends Command {
    private final UsersManager usersManager;
    private final CommandLine commandLine;

    public AddUser(UsersManager usersManager, CommandLine commandLine) {
        super("add_user", "|| add new user", 0);
        this.usersManager = usersManager;
        this.commandLine = commandLine;
    }

    @Override
    public String execute() {
        usersManager.addUser(new UserFactory(commandLine).getUser());
        return "User was added successfully!";
    }
}
