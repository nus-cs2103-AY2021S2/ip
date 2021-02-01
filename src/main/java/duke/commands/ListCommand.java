package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    /**
     * Prints all tasks in taskList.
     * @return String representation of all tasks in taskList.
     */
    @Override
    public String execute() {
        return this.taskList.getListInString();
    }
}
