package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Exits programme
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }
}
