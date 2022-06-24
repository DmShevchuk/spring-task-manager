package utils;

import commands.Command;
import commands.CommandFactory;
import exceptions.IncorrectCommandException;
import tasks.TaskManager;
import users.UsersManager;

import java.util.Scanner;

/**
 * Класс с бесконечным циклом для считывания ввода пользователя и вызова команд
 * */

public class CommandLine {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskManager taskManager;
    private final UsersManager usersManager;

    public CommandLine(UsersManager usersManager, TaskManager taskManager) {
        this.usersManager = usersManager;
        this.taskManager = taskManager;
    }

    public void run() {
        String userPrefix = ">>";

        CommandFactory commandFactory = new CommandFactory(usersManager, taskManager, this);
        // Вывод префикса для пользовательского ввода
        System.out.print(userPrefix);

        while (scanner.hasNext()) {
            String line = getLine();
            try {
                Command command = commandFactory.getCommand(line);
                System.out.println(command.execute());
            } catch (IncorrectCommandException e) {
                System.out.println(e.getMessage());
            }
            System.out.print(userPrefix);
        }
    }

    public String getLine() {
        return scanner.nextLine().strip();
    }
}
