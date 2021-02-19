package duke.commands;

import duke.tasks.TaskList;

/**
 * ErrorCommand is a command that indicates an error with the user-input command.
 */
public class ErrorCommand extends Command {
    String errorDescription;

    public ErrorCommand(TaskList taskList, String errorDescription) {
        super(taskList);
        this.errorDescription = errorDescription;
    }

    @Override
    public Command process() {
        return this;
    }

    @Override
    public TaskList execute() {
        return this.getTaskList();
    }

    @Override
    public String toString() {
        return this.errorDescription;
    }
}
