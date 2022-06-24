package users;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
public class User {
    @Getter
    @Setter
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

    public void removeTask(Task task) {
        taskList.remove(task);
    }


    @Override
    public String toString() {
        return String.format("id=%d, name=%s, task quantity = %d", id, name, taskList.size());
    }
}
