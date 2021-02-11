package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * class Command
 * @author Png Zheng Jie, Sebastian
 * Description: An abstract class to represent executable user commands
 */
public abstract class Command {
    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * isExit: checks if Duke should terminate
     * @return true if ByeCommand is executed
     */
    public abstract boolean isExit();
}
