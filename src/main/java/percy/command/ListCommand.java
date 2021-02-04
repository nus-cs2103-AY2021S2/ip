package percy.command;

import percy.storage.Storage;
import percy.task.TaskList;
import percy.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    /**
     * Constructs list command.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the List command which lists out the tasks.
     *
     * @param taskList The TaskList from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) {
        return Ui.makeListMsg(taskList);
    }
}
