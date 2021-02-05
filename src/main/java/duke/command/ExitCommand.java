package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * Command type exit.
 */
public class ExitCommand extends Command {

    /**
     * Execute and print a exit command.
     *
     * @param list Passes TaskList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public String executeAndPrint(TaskList list) throws DukeException {
        return "";
    }

    @Override
    public String toString() {
        return "Test usage: this is an EXIT command.";
    }
}
