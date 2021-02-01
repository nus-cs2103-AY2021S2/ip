package duke.command;

import duke.DukeException;
import duke.TaskList;

public class DoneCommand extends Command {

    public DoneCommand(String[] commandSplit) {
        super(commandSplit);
    }

    /**
     * Marks the user defined task as done.
     * @param list the task list.
     * @throws DukeException if failed to mark task as done.
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandSplit[1]);
            return list.done(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid task number to mark a task as done.");
        }
    }

}
