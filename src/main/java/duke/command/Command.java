package duke.command;

import duke.exceptions.DukeOutOfBoundsException;
import duke.exceptions.DukeStorageException;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.MessageGenerator;

/**
 * The Command class provides the contract by which all commands must abide by.
 * All commands should be able to be executed and return
 * a command result. They must have access to the model and the storage,
 * as well as a class for generating the messages to put in the
 * CommandResult class ( if need be).
 */
public abstract class Command {

    /**
     * Executes a sequence of high - level instructions and returns a CommandResult data structure
     * containing the data obtained after
     * running the execute method.
     *
     * @param messageGenerator The class to handle formatting and generating UI display messages
     * @param tasks The model of the data
     * @param storage The class responsible to save data onto the hard disk
     * @return CommandResult, a resulting data obtained after executing the command.
     * @throws DukeStorageException
     * @throws DukeOutOfBoundsException
     */
    public abstract CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage)
            throws DukeStorageException, DukeOutOfBoundsException;
}
