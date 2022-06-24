package commands.impl.tasks;

import commands.Command;

public class DeleteTaskById extends Command {
    public DeleteTaskById(){
        super("delete_task_by_id", "|| {id} delete task by id", 1);
    }

    @Override
    public String execute() {
        return null;
    }
}
