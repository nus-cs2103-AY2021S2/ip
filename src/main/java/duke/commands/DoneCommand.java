package duke.commands;

import duke.parser.InsufficientArgumentsException;
import duke.parser.WrongArgumentException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

/**
 * DoneCommand is a command that sets a specific task as completed.
 */
public class DoneCommand extends Command{
    private int indexOfTaskDone;

    // for executing
    public DoneCommand(TaskList taskList, int indexOfTaskDone) {
        super(taskList);
        this.indexOfTaskDone = indexOfTaskDone;
    }

    // for processing
    public DoneCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
        this.indexOfTaskDone = -1;
    }

    @Override
    public Command process() {
        try {
            if (this.getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of done cannot be empty.");
            }
            int taskIndex = Integer.parseInt(this.getUserInput()[1]);
            if (taskIndex > this.getTaskList().size() || taskIndex <= 0) {
                throw new WrongArgumentException("The task " + taskIndex + " does not exists.\n"
                        + "Please indicate a positive task number smaller or equal to "
                        + this.getTaskList().size() + ".");
            }
            return new DoneCommand(this.getTaskList(), taskIndex);
        } catch (InsufficientArgumentsException | WrongArgumentException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        } catch (NumberFormatException e) {
            return new ErrorCommand(this.getTaskList(),
                    "Please enter a positive number for the task that is completed.");
        }
    }

    @Override
    public TaskList execute() {
        ArrayList<Task> tasks = this.getTaskList().getList();
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            if (i == this.indexOfTaskDone - 1) {
                tasks.set(i, tasks.get(i).markAsDone());
                break;
            }
        }
        assert (tasks.size() == size);  // check that marking a task as done does not remove the task
        return new TaskList(tasks);
    }

    @Override
    public String toString() {
        TaskList tasks = this.getTaskList();
        String message = "Nice! I've marked this task as done:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            if (i == indexOfTaskDone - 1) {
                message += tasks.get(i).markAsDone().toString();
            }
        }
        return message;
    }
}
