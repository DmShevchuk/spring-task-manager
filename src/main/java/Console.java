import commands.Command;
import commands.CommandFactory;
import exceptions.IncorrectCommandException;
import tasks.TaskManager;

import java.util.Scanner;

public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private final CommandFactory commandFactory;
    private final TaskManager taskManager;

    public Console(CommandFactory commandFactory, TaskManager taskManager){
        this.commandFactory = commandFactory;
        this.taskManager = taskManager;
    }

    public void run(){
        System.out.println("Enter your instructions:");
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            try {
                Command command = commandFactory.getCommand(line);
                System.out.println(command.execute());
            } catch (IncorrectCommandException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
