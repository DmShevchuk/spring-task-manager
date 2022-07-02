//package commands.impl;
//
//import com.opencsv.exceptions.CsvDataTypeMismatchException;
//import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
//import commands.Command;
//import tasks.TaskManager;
//import users.UsersManager;
//import utils.SaverCSV;
//
//import java.io.IOException;
//
//public class Save extends Command {
//    private final UsersManager usersManager;
//    private final TaskManager taskManager;
//
//    public Save(UsersManager usersManager, TaskManager taskManager) {
//        super("save", "|| save all changes in tasks and users collection to file", 0);
//        this.usersManager = usersManager;
//        this.taskManager = taskManager;
//    }
//
//    @Override
//    public String execute() {
//        try {
//            SaverCSV saverCSV = new SaverCSV();
//            saverCSV.saveUsers(usersManager.getUsersList());
//            saverCSV.saveTasks(taskManager.getTaskCollection());
//        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
//            return "Unable to save data in file: " + e.getMessage() + "!";
//        }
//        return "Data was saved successfully!";
//    }
//}
