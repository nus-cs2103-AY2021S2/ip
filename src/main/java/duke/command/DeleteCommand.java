package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;

import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand implements Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.taskNum > tasks.size()) {
            throw new DukeException("No such task exists!");
        }
        Task deletedTask = tasks.get(taskNum);
        tasks.delete(taskNum);
        ui.printMessage("Noted. I've removed this task:\n" + "  " + deletedTask + "Now you have "
                + tasks.size() + " tasks in the list.");
    }
    
}
