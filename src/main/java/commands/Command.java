package commands;

import java.io.IOException;

import data.TaskList;
import ui.TextUi;

/**
 * The base Command class that all commands will need to extend.
 * Only subclasses are meant to be used.
 */
public abstract class Command {
    protected Command() {}

    /**
     * Executes the command's logic
     * @param tasks
     * @param ui
     * @throws IOException
     */
    public abstract void execute(TaskList tasks, TextUi ui) throws IOException;
}
