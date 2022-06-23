import com.opencsv.exceptions.CsvException;
import tasks.Task;
import tasks.TaskManager;
import users.User;
import users.UsersManager;
import utils.CommandLine;
import utils.ParserCSV;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Главный класс, точка входа в программу
 */

public class App {
    public static void main(String[] args) {
        String userFileName = "";
        String taskFileName = "";

        try {
            userFileName = new File(args[0]).getAbsolutePath();
            taskFileName = new File(args[1]).getAbsolutePath();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Enter file name for users and tasks!");
            System.exit(0);
        }

        ParserCSV parserCSV = new ParserCSV();
        List<User> usr = new ArrayList<>();
        List<Task> task = new ArrayList<>();
        try {
            usr = parserCSV.parseUser(userFileName);
            task = parserCSV.parseTask(taskFileName);
        } catch (IOException | CsvException e) {
            System.out.println("Unable to read files with initial data!\n" + "Description: " + e.getMessage());
            System.exit(0);
        }

        UsersManager usersManager = new UsersManager(usr);
        TaskManager taskManager = new TaskManager(task, usersManager);

        CommandLine cmd = new CommandLine(taskManager);
        cmd.run();
    }
}
