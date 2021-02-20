package commands;

import data.TaskList;
import ui.TextUi;

/**
 * The base Command class that all commands will need to extend.
 * Only subclasses are meant to be used.
 */
public abstract class Command {
    protected Command() {
    }

    /**
     * Executes the command's logic
     *
     * @param tasks
     * @param ui
     * @return response message
     */
    public abstract String execute(TaskList tasks, TextUi ui);
}
