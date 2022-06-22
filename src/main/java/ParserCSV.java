import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import exceptions.UnableToReadFileException;
import tasks.Task;
import tasks.TaskType;
import users.User;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParserCSV {
    public List<User> parseUser(String filePath) throws IOException, CsvException {
        File file = new File(filePath);
        if (!file.canRead()) {
            throw new UnableToReadFileException(String.format("Cannot read file with users %s!", filePath));
        }
        List<String[]> userLines;
        userLines = parse(file);
        List<User> userList = new ArrayList<>();
        for(String[] line: userLines){
            try {
                userList.add(new User(
                        // User id
                        Integer.parseInt(line[0]),
                        // User name
                        line[1]
                ));
            }catch (IndexOutOfBoundsException | ClassCastException e){
                System.out.println("Unable to read string " + Arrays.toString(line) + "!");
            }
        }
        return userList;
    }

    public List<Task> parseTask(String filePath) throws IOException, CsvException {
        File file = new File(filePath);
        if (!file.canRead()) {
            throw new UnableToReadFileException(String.format("Cannot read file with tasks %s!", filePath));
        }
        List<String[]> taskLines;
        taskLines = parse(file);
        List<Task> taskList = new ArrayList<>();
        for(String[] line: taskLines){
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            try {
                taskList.add(new Task(
                        // id
                        Integer.parseInt(line[0].trim()),
                        // title
                        line[1].trim(),
                        // description
                        line[2].trim(),
                        // owner id
                        Integer.parseInt(line[3].trim()),
                        // deadline
                        formatter.parse(line[4].trim()),
                        // task type
                        TaskType.getByString(line[5].trim())
                ));
            }catch (IndexOutOfBoundsException | ClassCastException e){
                System.out.println("Unable to read string " + Arrays.toString(line) + "!");
            } catch (ParseException e) {
                System.out.println("Unable to read date " + Arrays.toString(line) + ", format is dd.MM.yyyy!");
            }
        }
        return taskList;
    }


    private List<String[]> parse(File file) throws IOException, CsvException {
        Reader reader = Files.newBufferedReader(Path.of(file.toURI()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list;
        list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

}
