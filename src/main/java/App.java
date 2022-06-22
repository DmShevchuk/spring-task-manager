import com.opencsv.exceptions.CsvException;
import commands.CommandFactory;
import tasks.Task;
import tasks.TaskManager;
import users.User;
import users.UsersManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args){
        String userFileName = "";
        String taskFileName = "";
        try {
            userFileName = new File(args[0]).getAbsolutePath();
            taskFileName = new File(args[1]).getAbsolutePath();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Enter file name for users and tasks!");
            System.exit(0);
        }

        ParserCSV parserCSV = new ParserCSV();
        List<User> usr = new ArrayList<>();
        List<Task> task = new ArrayList<>();
        try {
            usr = parserCSV.parseUser(userFileName);
            task = parserCSV.parseTask(taskFileName);
        }catch (IOException | CsvException e){
            System.out.println("Unable to read files with initial data!\n" +
                    "Description: " + e.getMessage());
        }

        UsersManager usersManager = new UsersManager(usr);
        TaskManager taskManager = new TaskManager(task);
        CommandFactory commandFactory = new CommandFactory(taskManager, usersManager);
        Console console = new Console(commandFactory, taskManager);
        console.run();
    }
}
