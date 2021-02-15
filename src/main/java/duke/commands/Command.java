package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.DukeResponses;

/**
 * class Command
 * @author Png Zheng Jie, Sebastian
 * Description: An abstract class to represent executable user commands
 */
public abstract class Command {
    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param dukeResponses
     * @param storage
     * @return execution message
     */
    public abstract String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage);
}
