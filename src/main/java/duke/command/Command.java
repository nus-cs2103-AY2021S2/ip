package duke.command;

import duke.exception.DukeException;

/**
 * Parent class for all commands ued in Duke
 */
public class Command {

    /**
     * Constructor
     */
    protected Command() {

    }

    /**
     * Executes the action of adding the task.
     * Only used in the child classes
     * @return the corresponding results to be printed to user
     * @throws DukeException when the method is called in a Command class object rather than its
     *          specific child class
     */
    public String[] execute() throws DukeException {
        throw new DukeException("Parent command class does not have this method.");
    }
}
