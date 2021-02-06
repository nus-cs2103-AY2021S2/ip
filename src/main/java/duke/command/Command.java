package duke.command;

import duke.DukeException;
import duke.PlaceList;
import duke.TaskList;

/**
 * Abstract class for Tasks.
 */
public abstract class Command {

    /**
     * Command execution prototype.
     *
     * @param listT Passes TaskList in case of reading and writing to the list.
     * @param listP Passes PlaceList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    public abstract String executeAndPrint(TaskList listT, PlaceList listP) throws DukeException;

}
