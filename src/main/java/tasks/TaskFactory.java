package tasks;

import utils.CommandLine;
import utils.InputParser;

/**
 * Класс для получения полей объекта класса {@link Task} из ввода пользователя
 */
public class TaskFactory {
    private final CommandLine commandLine;

    public TaskFactory(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public Task.TaskBuilder getTask() {
        InputParser inputParser = new InputParser(commandLine);

        Task.TaskBuilder taskBuilder = Task.builder();

        taskBuilder.title(inputParser.parseString("Title:"));
        taskBuilder.description(inputParser.parseString("Description:"));
        taskBuilder.ownerId(inputParser.parseInteger());
        taskBuilder.deadline(inputParser.parseDate());
        taskBuilder.type(inputParser.parseTaskType());

        return taskBuilder;

    }
}
