package tasks;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private final int id;
    private final String title;
    private final String description;
    private final Date deadline;
    private TaskType type;
    private final int ownerId;

    public Task(int id,
                String title,
                String description,
                int ownerId,
                Date date,
                TaskType type){
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = date;
        this.type = type;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setType(TaskType type){
        this.type = type;
    }

    public TaskType getType() {
        return type;
    }

    public int getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return String.format("id=%d, title=%s, description=%s, deadline=%s, type=%s",
                id, title, description, sdf.format(deadline), type.getTitle());
    }
}
