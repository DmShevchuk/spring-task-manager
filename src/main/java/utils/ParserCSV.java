package utils;

import com.opencsv.bean.CsvToBeanBuilder;
import tasks.Task;
import users.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ParserCSV {

    public List<User> parseUsers(String filePath) throws FileNotFoundException {
        return new CsvToBeanBuilder<User>(new FileReader(filePath))
                .withType(User.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
    }

    public List<Task> parseTasks(String filePath) throws FileNotFoundException {
        return new CsvToBeanBuilder<Task>(new FileReader(filePath))
                .withType(Task.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
    }
}
