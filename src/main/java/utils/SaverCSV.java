package utils;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import tasks.Task;
import users.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Класс, выполняющий сохранение списка объектов класса {@link User} и {@link Task}
 * */

public class SaverCSV {
    public void saveUsers(List<User> userList) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String defaultFileForUsers = "users.csv";

        Writer writer = new FileWriter(defaultFileForUsers);
        StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(writer).build();
        beanToCsv.write(userList);
        writer.close();
    }

    public void saveTasks(List<Task> taskList) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String defaultFileForTasks = "tasks.csv";

        Writer writer = new FileWriter(defaultFileForTasks);
        StatefulBeanToCsv<Task> beanToCsv = new StatefulBeanToCsvBuilder<Task>(writer).build();
        beanToCsv.write(taskList);
        writer.close();
    }
}
