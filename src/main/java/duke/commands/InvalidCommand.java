package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * class InvalidCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an invalid command input by the user
 */
public class InvalidCommand extends Command {
    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommands();
    }

    /**
     * isExit: checks if Duke should terminate
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
