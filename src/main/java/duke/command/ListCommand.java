package duke.command;
import java.io.IOException;

import duke.exception.DukeException;

/**
 * Command class to list task in the task list
 */
public class ListCommand extends Command {

    /**
     * Create a list command
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Display all tasks found in the task list
     * @return String message upon successful execution of the command
     */
    @Override
    public String execute() throws DukeException, IOException {
        tasklist.setTaskList(storage.loadFile());
        String allTaskMsg = tasklist.showAllTask("list");

        if (allTaskMsg.isEmpty()) {
            return ui.displayNoTaskMessage();
        } else {
            return allTaskMsg;
        }
    }

}
