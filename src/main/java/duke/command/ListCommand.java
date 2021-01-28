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
     * @param length For printer to call newLiner, make Duke looks nice.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public void executeAndPrint(TaskList list, int length) throws DukeException {
        if (list.getSize() == 0) {
            System.out.print("List is empty\n");
        } else {
            System.out.print(list.formatList(length));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Test usage: this is a LIST command";
    }
}
