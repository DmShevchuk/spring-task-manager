package commands.impl.users;

import commands.Command;

public class AddUser extends Command {
    public AddUser(){
        super("add_user", "|| add new user", 0);
    }
    @Override
    public String execute() {
        return null;
    }
}
