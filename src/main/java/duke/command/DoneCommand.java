package duke.command;

import duke.DukeException;
import duke.StringParser;
import duke.TaskList;
import duke.task.Task;

/**
 * Command type done.
 */
public class DoneCommand extends Command {

    private final String command;

    /**
     * Done command builder.
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
            index = Integer.parseInt(command.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Argument: Input must to be integer");
        }
        if (index < list.getSize() && index >= 0) {
            Task currTask = list.getJob(index);
            currTask.markAsDone();
            list.replaceJob(index, currTask);
            return "This task is marked as done: \n"
                    + currTask.toString();
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
        return "Test usage: this is a DONE command";
    }
}
