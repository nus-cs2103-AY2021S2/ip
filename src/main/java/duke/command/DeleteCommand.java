package duke.command;

import duke.DukeException;
import duke.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] commandSplit) {
        super(commandSplit);
    }

    /**
     * Deletes the user specified task from the task list.
     * @param list the task list.
     * @throws DukeException if failed to remove task from task list.
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        int taskNumber = Integer.parseInt(commandSplit[1]);
        return list.remove(taskNumber);
    }
}
