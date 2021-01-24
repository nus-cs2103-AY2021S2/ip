package duke.commands;

import duke.exceptions.*;

import duke.tasks.TaskList;

import duke.ui.Ui;

import duke.storage.Storage;

import java.io.IOException;

/**
 * Abstract class responsible for executing command line inputs.
 */
public abstract class Command {
    /**
     * Executes the command specified in the commannd line input.
     *
     * @param tasks TaskList containing tasks.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @throws IOException If error happens while saving contents into save file.
     * @throws DukeException If error happens while executing command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    /**
     * Returns if program should exit after this command.
     *
     * @return true if should program should exit after command, false otherwise.
     */
    public abstract boolean isExit();
}
