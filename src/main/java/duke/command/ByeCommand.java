package duke.command;

import duke.DukeException;
import duke.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String[] commandSplit) {
        super(commandSplit);
    }

    /**
     * Rewrites all Tasks in the list to the storage before saying bidding user goodbye.
     * @param list the task list.
     * @throws DukeException if failed to rewrite tasks.
     */
    public String execute(TaskList list) throws DukeException {
        list.rewriteTasks();
        return ("Bye. Hope to see you again soon!");
    }
}
