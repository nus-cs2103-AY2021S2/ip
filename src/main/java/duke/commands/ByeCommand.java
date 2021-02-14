package duke.commands;

import duke.tasks.TaskList;

public class ByeCommand extends Command {

    public ByeCommand(TaskList taskList) {
        super(taskList);
    }

    public TaskList execute() {
        return this.getTaskList();
    }

    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}