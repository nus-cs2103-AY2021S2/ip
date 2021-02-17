package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

/**
 * class HelpCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "help"
 */
public class HelpCommand extends Command {
    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param dukeResponses
     * @param storage
     * @return list of commands recognised by Duke
     */
    public String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage) {
        return dukeResponses.showCommands();
    }
}
