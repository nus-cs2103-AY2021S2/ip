package duke.commands;

import duke.tasks.TaskList;

public class ByeCommand extends Command {

    public ByeCommand(TaskList taskList) {
        super(taskList);
    }

    public ByeCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
    }

    public Command process() {
        return new ByeCommand(this.getTaskList());
    }

    public TaskList execute() {
        return this.getTaskList();
    }

    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}