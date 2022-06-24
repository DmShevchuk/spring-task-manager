package utils;

import tasks.TaskType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, отвечающий за парсинг данных из ввода пользователя
 * */
public class InputParser {
    private final CommandLine commandLine;

    public InputParser(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    public int parseInteger() {
        while (true) {
            System.out.print("Owner id:");
            String inputLine = commandLine.getLine();
            try {
                return Integer.parseInt(inputLine);
            } catch (NumberFormatException | ClassCastException e) {
                System.out.println("Unable to get id from '" + inputLine + "'!");
            }
        }
    }

    public Date parseDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        while (true) {
            System.out.print("Enter deadline (format is dd.MM.yyyy):");
            String inputLine = commandLine.getLine();
            try {
                return formatter.parse(inputLine);
            } catch (ParseException e) {
                System.out.println("Unable to get date from '" + inputLine + "'!");
            }
        }
    }

    public String parseString(String message) {
        while (true) {
            System.out.print(message);
            String inputLine = commandLine.getLine();
            if (inputLine.length() != 0) {
                return inputLine;
            }
            System.out.println("Unable to read title from empty string!");
        }
    }

    public TaskType parseTaskType() {
        System.out.print("Enter task type (new, in progress, done): ");
        return TaskType.getByString(commandLine.getLine());
    }
}
