package duke.commands;

import duke.parser.InsufficientArgumentsException;
import duke.parser.WrongArgumentException;
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
            int taskIndex = Integer.parseInt(this.getUserInput()[1]);
            if (taskIndex > this.getTaskList().size() || taskIndex <= 0) {
                throw new WrongArgumentException("The task " + taskIndex + " does not exists.\n"
                        + "Please indicate a positive task number smaller or equal to "
                        + this.getTaskList().size() + ".");
            }
            return new DeleteCommand(this.getTaskList(), taskIndex);
        } catch (InsufficientArgumentsException | WrongArgumentException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        } catch (NumberFormatException e) {
            return new ErrorCommand(this.getTaskList(),
                    "Please enter a positive number for the task to be deleted.");
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