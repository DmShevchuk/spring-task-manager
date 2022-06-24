package commands;

import lombok.Getter;

public abstract class Command {
    @Getter
    private final String name;
    @Getter
    private final String info;
    @Getter
    private final int argsQuantity;

    public Command(String name, String info, int argsQuantity) {
        this.name = name;
        this.info = info;
        this.argsQuantity = argsQuantity;
    }

    public abstract String execute();
}
