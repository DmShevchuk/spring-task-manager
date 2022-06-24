package commands.impl.tasks;

import commands.Command;
import tasks.Task;
import tasks.TaskManager;
import tasks.TaskType;
import utils.CommandLine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, реализующий функционал добавления задачи в список
 **/
public class AddTask extends Command {
    private final TaskManager taskManager;
    private final CommandLine commandLine;

    public AddTask(TaskManager taskManager, CommandLine commandLine) {
        super("add_task", "|| add new task", 0);
        this.taskManager = taskManager;
        this.commandLine = commandLine;
    }

    @Override
    public String execute() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        int id, ownerId;
        String title, description;
        Date deadline;
        TaskType type;

        id = parseInteger("Enter task id:");
        ownerId = parseInteger("Enter owner id:");

        System.out.print("Enter task title:");
        title = commandLine.getLine();

        System.out.print("Enter task description:");
        description = commandLine.getLine();

        while (true) {
            System.out.print("Enter deadline:");
            String inputLine = commandLine.getLine();
            try {
                deadline = formatter.parse(inputLine);
                break;
            } catch (ParseException | NumberFormatException e) {
                System.out.println("Unable to get id from '" + inputLine + "'!");
            }
        }

        System.out.println("Enter task type (NEW status is default): ");
        type = TaskType.getByString(commandLine.getLine());
        taskManager.add(new Task(id, title, description, ownerId, deadline, type));
        return "Task was added successfully!";
    }

    // Парсинг id и ownerId из ввода пользователя
    private int parseInteger(String message) {
        while (true) {
            System.out.print(message);
            String inputLine = commandLine.getLine();
            try {
                return Integer.parseInt(inputLine);
            } catch (NumberFormatException | ClassCastException e) {
                System.out.println("Unable to get id from '" + inputLine + "'!");
            }
        }
    }
}
