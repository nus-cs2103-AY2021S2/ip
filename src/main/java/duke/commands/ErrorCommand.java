package duke.commands;

import duke.tasks.TaskList;

public class ErrorCommand extends Command {
    String errorDescription;

    public ErrorCommand(TaskList taskList, String errorDescription) {
        super(taskList);
        this.errorDescription = errorDescription;
    }

    public Command process() {
        return this;
    }

    public TaskList execute() {
        return this.getTaskList();
    }

    public String toString() {
        return this.errorDescription;
    }
}
