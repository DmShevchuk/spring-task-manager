package commands.impl.users;

import commands.Command;

public class ShowUsers extends Command {
    public ShowUsers(){
        super("show_users", "|| show list of users", 0);
    }
    @Override
    public String execute() {
        return null;
    }
}
