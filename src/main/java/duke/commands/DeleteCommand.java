package duke.commands;

import duke.parser.InsufficientArgumentsException;
import duke.tasks.TaskList;

/**
 * DeleteCommand is a command that deletes a specified task from the task list.
 */
public class DeleteCommand extends Command {
    private int indexOfTaskToDelete;

    public DeleteCommand(TaskList taskList, int indexOfTaskToDelete) {
        super(taskList);
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }
    public DeleteCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
        this.indexOfTaskToDelete = -1;
    }

    @Override
    public Command process() {
        try {
            if (this.getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of delete cannot be empty.");
            }
            return new DeleteCommand(this.getTaskList(), Integer.parseInt(this.getUserInput()[1]));
        } catch (InsufficientArgumentsException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        }
    }

    @Override
    public TaskList execute() {
        TaskList tasks = this.getTaskList();
        int initialSize = tasks.size();
        tasks.remove(this.indexOfTaskToDelete - 1);
        assert (initialSize - 1 == tasks.size());   // check that task is properly deleted
        return tasks;
    }

    @Override
    public String toString() {
        String message = "Noted. I've removed this task: \n";
        message += this.getTaskList().get(this.indexOfTaskToDelete - 1).toString() + "\n";
        message += " Now you have " + (this.getTaskList().size() - 1) + " tasks in the list.";
        return message;
    }
}