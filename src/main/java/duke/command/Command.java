package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * Abstract class for Tasks.
 */
public abstract class Command {

    /**
     * Command execution prototype.
     *
     * @param list Passes TaskList in case of reading and writing to the list.
     * @param length For printer to call newLiner, make Duke looks nice.
     * @throws DukeException When encounter an error in command argument.
     */
    public abstract void executeAndPrint(TaskList list, int length) throws DukeException;
    public abstract boolean isExit();
}
