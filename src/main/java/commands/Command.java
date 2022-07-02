package commands;

import exceptions.CommandExecutionException;
import exceptions.TaskNotFoundException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import lombok.Getter;
import lombok.Setter;

public abstract class Command {
    @Getter
    private final String name;
    @Getter
    private final String info;
    protected final int argsQuantity;
    @Setter
    protected String arg = "";

    public Command(String name, String info, int argsQuantity) {
        this.name = name;
        this.info = info;
        this.argsQuantity = argsQuantity;
    }

    public abstract String execute() throws CommandExecutionException, UserNotFoundException, UserAlreadyExistsException, TaskNotFoundException;

    protected String[] getArgsAsArray() {
        if ("".equals(arg)) {
            return new String[]{};
        }

        return arg.split("\\s*,\\s*");
    }

    protected void resetArgs(){
        arg = "";
    }
}
