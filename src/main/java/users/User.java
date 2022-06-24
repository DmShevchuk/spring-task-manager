package users;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class User {
    @Getter
    @CsvBindByPosition(position = 0, required = true)
    private int id;

    @Getter
    @Setter
    @CsvBindByPosition(position = 1, required = true)
    private String name;

    @Getter
    @CsvIgnore
    private final List<Task> taskList = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    @Override
    public String toString() {
        return String.format("id=%d, name=%s", id, name);
    }
}
