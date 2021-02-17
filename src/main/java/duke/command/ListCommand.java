package duke.command;

import duke.task.TaskList;

/**
 * Sub-class of Command that represents and executes the "list" instruction of user.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand object that lists tasks upon execution.
     */
    public ListCommand() {
        super("", "", "", false, command -> TaskList.listTasks());
    }
}
