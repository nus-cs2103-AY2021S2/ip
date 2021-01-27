package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        Task removedTask = tasks.deleteTask(indexToDelete);
        storage.storeData(tasks);
        ui.print("I've removed this task:\n\t\t" + removedTask +
                "\n\n\t  You have " +
                tasks.getSize() + (tasks.getSize() == 1 ? " task" : " tasks") + " in your list");
        return true;
    }
}
