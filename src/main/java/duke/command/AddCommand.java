package duke.command;

import duke.main.DukeException;
import duke.main.Task;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class AddCommand extends Command {
    private Task taskToAdd;
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String[] execute() {
        String[] res;
        try {
            res = TaskList.addTask(taskToAdd);
            Storage.saveData();
            return res;
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }
}
