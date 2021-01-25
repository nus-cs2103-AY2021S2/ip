package duke.command;

import duke.main.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class DeleteCommand extends Command{
    private int taskToDelete;
    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public String[] execute() {
        String[] res;
        try {
            res = TaskList.deleteTask(taskToDelete);
            Storage.saveData();
            return res;
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }
}
