package utils;

import commands.Command;
import commands.CommandFactory;
import exceptions.IncorrectCommandException;
import tasks.TaskManager;

import java.util.Scanner;

/**
 * Класс с бесконечным циклом для считывания ввода пользователя и вызова команд
 * */
public class CommandLine {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskManager taskManager;

    public CommandLine(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void run() {
        String userPrefix = ">>";

        CommandFactory commandFactory = new CommandFactory(taskManager, this);
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
