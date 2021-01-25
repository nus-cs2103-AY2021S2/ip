package duke.command;

import duke.main.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class DoneCommand extends Command {
    private int taskToComplete;
    public DoneCommand(int taskToComplete) {
        this.taskToComplete = taskToComplete;
    }

    @Override
    public String[] execute() {
        String[] res;
        try {
            res = TaskList.completeTask(taskToComplete);
            Storage.saveData();
            return res;
        } catch (DukeException e) {
            return new String[]{e.getMessage()};
        }
    }
}
