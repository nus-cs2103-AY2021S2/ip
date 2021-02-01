package duke.command;

import duke.task.TaskList;

/**
 * An interface that represents a command that applies when executed.
 */
public interface Command {
    /**
     * Returns the result to be printed on executing the command.
     *
     * @param taskList the task list to be modified by the command.
     * @return the result of the command to be printed.
     */
    String execute(TaskList taskList);
}
