package utils;

import commands.Command;
import commands.CommandFactory;
import exceptions.IncorrectCommandException;
import tasks.TaskManager;
import users.UsersManager;

import java.util.Scanner;

public class CommandLine {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskManager taskManager;
    private final UsersManager usersManager;

    public CommandLine(TaskManager taskManager, UsersManager usersManager){
        this.taskManager = taskManager;
        this.usersManager = usersManager;
    }

    public void run(){
        CommandFactory commandFactory = new CommandFactory(taskManager, usersManager, this);
        System.out.print(">>");
        while (scanner.hasNext()){
            String line = getLine();
            try {
                Command command = commandFactory.getCommand(line);
                System.out.println(command.execute());
            } catch (IncorrectCommandException e){
                System.out.println(e.getMessage());
            }
            System.out.print(">>");
        }
    }

    public String getLine(){
        return scanner.nextLine().strip();
    }
}
