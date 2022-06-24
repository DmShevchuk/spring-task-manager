package utils;

import commands.Command;
import commands.CommandFactory;
import exceptions.CommandExecutionException;
import exceptions.IncorrectArgsQuantityException;
import exceptions.IncorrectCommandException;
import tasks.TaskManager;
import users.UsersManager;

import java.util.Scanner;

/**
 * Класс с бесконечным циклом для считывания ввода пользователя и вызова команд
 */

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
        System.out.print(userPrefix);

        CommandFactory commandFactory = new CommandFactory(usersManager, taskManager, this);

        while (scanner.hasNext()) {
            String line = getLine();
            try {
                Command command = commandFactory.getCommand(line);
                int wordsQuantity = line.split("\\s").length;

                if (command.getArgsQuantity() + 1 == wordsQuantity) {
                    command.setArg(line.split("\\s")[wordsQuantity - 1]);
                    System.out.println(command.execute());
                } else {
                    throw new IncorrectArgsQuantityException(command.getArgsQuantity(), wordsQuantity - 1);
                }

            } catch (IncorrectCommandException | CommandExecutionException | IncorrectArgsQuantityException e) {
                System.out.println(e.getMessage());
            }
            System.out.print(userPrefix);
        }
    }

    public String getLine() {
        return scanner.nextLine().strip();
    }
}
