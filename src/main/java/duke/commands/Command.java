package duke.commands;

import duke.tasks.TaskList;

public abstract class Command {
    private TaskList taskList;

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract TaskList execute();

    public TaskList getTaskList() {
        return this.taskList;
    }

    public abstract String toString();
}
