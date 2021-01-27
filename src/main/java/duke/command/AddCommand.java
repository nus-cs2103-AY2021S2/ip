package duke.command;

import duke.TaskStorage;
import duke.Ui;
import duke.task.Task;
import duke.TaskList;

public class AddCommand extends Command {
    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        tasks.addTask(toAdd);
        storage.storeData(tasks);
        ui.print("Got it. I've added this task:\n\t\t" + toAdd +
                "\n\n\t  You have " +
                tasks.getSize() + (tasks.getSize() == 1 ? " task" : " tasks") + " in your list");
        return true;
    }
}
