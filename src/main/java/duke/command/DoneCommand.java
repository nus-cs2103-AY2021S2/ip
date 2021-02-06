package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;

/**
 * Command type done.
 */
public class DoneCommand extends Command {

    private static final int LENGTH_DONE = "done ".length();

    private static final String ERROR_INCORRECT_FORMAT = "Invalid Argument: Input must to be integer";
    private static final String MESSAGE_OBJECT_NOT_FOUND = "No such task in the list";

    private final String command;

    /**
     * Constructs a done command.
     *
     * @param command Input string.
     */
    public DoneCommand(String command) {
        this.command = command;
    }

    /**
     * Execute and print a done command.
     *
     * @param list Passes TaskList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public String executeAndPrint(TaskList list) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(command.substring(LENGTH_DONE)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_INCORRECT_FORMAT);
        }
        if (index < list.getSize() && index >= 0) {
            Task currTask = list.getJob(index);
            currTask.markAsDone();
            list.replaceJob(index, currTask);
            return "This task is marked as done: \n"
                    + currTask.toString();
        } else {
            return MESSAGE_OBJECT_NOT_FOUND + "\n";
        }
    }

    @Override
    public String toString() {
        return "Test usage: this is a DONE command";
    }
}
