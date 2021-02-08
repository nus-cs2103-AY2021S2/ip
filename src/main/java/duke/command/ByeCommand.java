package duke.command;

import duke.DukeException;
import duke.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit.length == 1 && commandSplit[0].equals("bye"): "Bye command should be \"bye\"";
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
