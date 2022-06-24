package commands.impl.tasks;

import commands.Command;

public class UpdateTaskById extends Command {
    public UpdateTaskById(){
        super("update_task_by_id", "|| {id} update task by id", 1);
    }
    @Override
    public String execute() {
        return null;
    }
}
