package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    /**
     * Executes the user command to print the task list.
     * @param tasks A TaskList object which encapsulates the data and operations on a task list.
     * @param ui A duke.Ui object which deals with interactions with the user.
     * @param storage A duke.Storage object which deals with loading tasks from the file and saving tasks in the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks(ui);
    }

    public boolean isExit() {
        return false;
    }
}
