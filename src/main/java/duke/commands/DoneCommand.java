package duke.commands;

import duke.parser.InsufficientArgumentsException;
import duke.parser.WrongArgumentException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

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

    public Command process() {
        try {
            if (this.getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of done cannot be empty.");
            }
            return new DoneCommand(this.getTaskList(), Integer.parseInt(this.getUserInput()[1]));
        } catch (InsufficientArgumentsException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        }
    }

    public TaskList execute() {
        ArrayList<Task> tasks = this.getTaskList().getList();
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            if (i == this.indexOfTaskDone - 1) {
                tasks.set(i, tasks.get(i).markAsDone());
            }
        }
        assert (tasks.size() == size);  // check that marking a task as done does not remove the task
        return new TaskList(tasks);
    }

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
