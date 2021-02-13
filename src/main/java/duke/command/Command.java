package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command used by the Duke chat bot.
 * It is represented by a command type, followed by
 * a series of arguments.
 */
public abstract class Command {

    public abstract CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
