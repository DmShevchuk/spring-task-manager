package tasks;

public enum TaskType {
    NEW("new"),
    IN_PROGRESS("progress"),
    DONE("done");

    private final String title;

    TaskType(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static TaskType getByString(String type){
        return switch (type) {
            case "progress" -> TaskType.IN_PROGRESS;
            case "done" -> TaskType.DONE;
            default -> TaskType.NEW;
        };
    }
}
