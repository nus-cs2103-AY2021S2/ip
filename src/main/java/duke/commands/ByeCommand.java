package duke.commands;

import duke.tasks.TaskList;

/**
 * ByeCommand is called when the user indicates that he has finished using the Duke application.
 */
public class ByeCommand extends Command {

    public ByeCommand(TaskList taskList) {
        super(taskList);
    }

    public ByeCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
    }

    @Override
    public Command process() {
        return new ByeCommand(this.getTaskList());
    }

    @Override
    public TaskList execute() {
        return this.getTaskList();
    }

    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}