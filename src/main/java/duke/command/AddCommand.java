package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand acting on the given task.
     * isExit is False.
     *
     * @param task
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Adds a new task to taskList
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(task);
        ui.printIndented("Got it. I've added this task:");
        ui.printIndented(String.format("    %s", task));
        ui.printIndented(String.format("Now you have %d tasks in the list.", taskList.size()));
        storage.write(taskList.toDataString());
    }
}
