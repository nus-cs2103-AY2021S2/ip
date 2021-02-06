package duke.command;

import duke.DukeException;
import duke.PlaceList;
import duke.TaskList;

public class PlaceCommand extends Command {

    private static final String MESSAGE_LIST_EMPTY = "List is empty";

    /**
     * Execute and print a list command.
     *
     * @param listT Passes TaskList in case of reading and writing to the list.
     * @param listP Passes PlaceList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public String executeAndPrint(TaskList listT, PlaceList listP) throws DukeException {
        if (listP.getSize() == 0) {
            return MESSAGE_LIST_EMPTY + "\n";
        } else {
            return listP.formatList();
        }
    }
}
