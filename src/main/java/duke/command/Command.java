package duke.command;

import duke.exceptions.DukeException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;

/**
 * Interface for all commands, which have an execute and executeGui method that performs an action
 */

public interface Command {
    /**
     * Executes the deadline command, which adds a deadline task to Duke's TaskList
     *
     * @param dukeTaskList Give dukeTaskList
     * @param dukeUi Give dukeUi
     * @param dukeStorage Give dukeStorage
     */
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException;

    /**
     * Executes the deadline command for the GUI, which adds a deadline task to Duke's TaskList
     *
     * @param dukeTaskList Give dukeTaskList
     * @param dukeUi Give dukeUi
     * @param dukeStorage Give dukeStorage
     * @return a String which represents the task that has occured
     * @throws DukeException throws DukeException
     */
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException;

}
