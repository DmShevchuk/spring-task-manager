package tasks;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@NoArgsConstructor
public class Task {
    @Getter
    @Setter
    @CsvBindByPosition(position = 0, required = true)
    private int id;

    @Getter
    @Setter
    @CsvBindByPosition(position = 1, required = true)
    private String title;

    @Getter
    @Setter
    @CsvBindByPosition(position = 2, required = true)
    private String description;

    @Getter
    @Setter
    @CsvBindByPosition(position = 3, required = true)
    private int ownerId;

    @Getter
    @Setter
    @CsvBindByPosition(position = 4, required = true)
    @CsvDate("dd.MM.yyyy")
    private Date deadline;

    @Getter
    @Setter
    @CsvBindByPosition(position = 5, required = true)
    private TaskType type;

    public Task(int id,
                String title,
                String description,
                int ownerId,
                Date date,
                TaskType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = date;
        this.type = type;
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        return String.format("id=%d, title=%s, description=%s, owner id = %d, deadline=%s, type=%s",
                id, title, description, ownerId, sdf.format(deadline), type.getTitle());
    }
}
