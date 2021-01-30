package duke.command;

import duke.DukeException;
import duke.StringParser;
import duke.TaskList;
import duke.task.Task;

/**
 * Command type delete.
 */
public class DeleteCommand extends Command {

    private final String command;

    /**
     * Delete command builder.
     *
     * @param command Input string.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Execute and print a delete command.
     *
     * @param list Passes TaskList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public String executeAndPrint(TaskList list) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(command.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Argument: Input must to be integer");
        }
        if (index < list.getSize() && index >= 0) {
            Task currTask = list.getJob(index);
            list.deleteJob(index);
            return "This task is deleted: \n"
                    + currTask.toString()
                    + "Now you have " + list.getSize()
                    + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n");
        } else {
            return "No such task in the list\n";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Test usage: this is a DELETE command";
    }
}
