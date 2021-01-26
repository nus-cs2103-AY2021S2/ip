package duke.command;

import duke.task.TaskList;

public interface Command {
    /**
     * Returns the result to be printed on executing the command.
     * @param taskList the task list to be modified by the command
     * @return the result of the command to be printed
     */
    String execute(TaskList taskList);
}
