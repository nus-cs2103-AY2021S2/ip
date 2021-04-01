package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;

/**
 * Represents the abstract command.
 */
public abstract class Command {

    public abstract CommandResponse getResponse(TaskList tasks, Storage storage) throws QuackersException;
}
