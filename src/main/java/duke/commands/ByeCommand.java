package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

/**
 * class ByeCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "bye"
 */
public class ByeCommand extends Command {
    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param dukeResponses
     * @param storage
     * @return goodbye message
     */
    public String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage) {
        return dukeResponses.showGoodbye();
    }
}
