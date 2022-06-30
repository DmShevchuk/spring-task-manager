package commands.impl;

import commands.Command;

public class Exit extends Command {
    public Exit() {
        super("exit", "|| exit from", 0);
    }

    @Override
    public String execute() {
        System.out.println("Session was closed!");
        System.exit(0);
        return null;
    }
}
