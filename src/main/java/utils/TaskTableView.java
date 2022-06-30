package utils;

import tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

/**
 * Класс для красивого табличного вывода элементов коллекции
 **/
public class TaskTableView {
    private List<Task> taskList;
    private List<String> columnNames;
    private int maxWidth;

    public TaskTableView(List<Task> lst, List<String> columnNames) {
        this.taskList = lst;
        this.columnNames = columnNames;
    }

    public String printCollection() {
        // Форматирование даты по шаблону
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        // Получение максимальной ширины слова в заголовках
        maxWidth = columnNames.stream().max(Comparator.comparing(String::length)).get().length();

        // Проход по всем задачам и получение ширины у каждого поля
        for (Task task : taskList) {
            List<Integer> widthList = List.of(
                    String.valueOf(task.getId()).length(),
                    task.getTitle().length(),
                    task.getDescription().length(),
                    String.valueOf(task.getOwnerId()).length(),
                    formatter.format(task.getDeadline()).length(),
                    String.valueOf(task.getType()).length());
            int currMaxWidth = widthList.stream().max(Comparator.comparingInt(Integer::intValue)).get();
            if (currMaxWidth > maxWidth) {
                maxWidth = currMaxWidth;
            }
        }

        StringBuilder resultTable = new StringBuilder();

        resultTable.append(addHorizontalLine());

        for (String title : columnNames) {
            String settings = "|%-" + maxWidth + "s";

            resultTable.append(String.format(settings, title));
        }

        resultTable.append("\n");
        resultTable.append(addHorizontalLine());

        for (Task task : taskList) {
            String settings = "|%-" + maxWidth + "s";

            resultTable.append(String.format(settings, task.getId()));
            resultTable.append(String.format(settings, task.getTitle()));
            resultTable.append(String.format(settings, task.getDescription()));
            resultTable.append(String.format(settings, task.getOwnerId()));
            resultTable.append(String.format(settings, formatter.format(task.getDeadline())));
            resultTable.append(String.format(settings, task.getType()));
            resultTable.append("\n");
        }
        return resultTable.toString();
    }

    private String addHorizontalLine() {
        return "-".repeat(Math.max(0, columnNames.size() * maxWidth)) + "\n";
    }
}
