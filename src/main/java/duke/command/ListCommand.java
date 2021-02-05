package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * Command type list.
 */
public class ListCommand extends Command {

    /**
     * Execute and print a list command.
     *
     * @param list Passes TaskList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public String executeAndPrint(TaskList list) throws DukeException {
        if (list.getSize() == 0) {
            return "List is empty.\n";
        } else {
            return list.formatList();
        }
    }

    @Override
    public String toString() {
        return "Test usage: this is a LIST command.";
    }
}
