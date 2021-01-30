package pason.commands;

import pason.storage.Storage;
import pason.tasks.Task;
import pason.tasks.TaskList;
import pason.ui.Ui;

public class AddCommand extends Command {
    protected Task task;
    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) throws Exception {
        tasks.addTask(task);
        ui.printMessage("Done! I've added a new task:\n\t"
                + task + "\nNow there are " + tasks.getTasks().size()
                        + " tasks in your list.");
    }

    public boolean isExit() {
        return false;
    }
}