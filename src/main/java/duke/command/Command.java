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
     * @throws DukeException When encounter an error in command argument.
     */
    public abstract String executeAndPrint(TaskList list) throws DukeException;
    public abstract boolean isExit();
}
