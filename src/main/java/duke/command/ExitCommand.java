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
     * @param length For printer to call newLiner, make Duke looks nice.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public void executeAndPrint(TaskList list, int length) throws DukeException {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Test usage: this is an EXIT command";
    }
}
