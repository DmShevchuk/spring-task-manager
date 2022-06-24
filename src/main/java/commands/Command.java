package commands;

import exceptions.CommandExecutionException;
import lombok.Getter;
import lombok.Setter;

public abstract class Command {
    @Getter
    private final String name;
    @Getter
    private final String info;
    @Getter
    private final int argsQuantity;
    @Setter
    public String arg;

    public Command(String name, String info, int argsQuantity) {
        this.name = name;
        this.info = info;
        this.argsQuantity = argsQuantity;
    }

    public abstract String execute() throws CommandExecutionException;
}
