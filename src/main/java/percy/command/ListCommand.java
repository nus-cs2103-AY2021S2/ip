package percy.command;

import percy.storage.Storage;
import percy.task.TaskList;
import percy.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {
    public static final String COMMAND = "list";
    public static final ArrayList<String> USAGE_GUIDE = new ArrayList<>(List.of(
            "list: Lists out all the tasks in numeric order.",
            "Parameters: NONE",
            "Example: list"));

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
