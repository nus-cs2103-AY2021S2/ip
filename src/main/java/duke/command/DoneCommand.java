package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private String fullCommand;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the done command.
     * Marks the specified task's status as done.
     *
     * @param tasks a list of tasks.
     * @param storage the storage of the Duke object.
     *
     * @return the output to be displayed to user.
     * @throws IOException If an input or output exception occurred
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        ArrayList<Task> taskList = tasks.getTasks();
        String output = "";
        try {
            int indexToMarkDone = Integer.parseInt(fullCommand.split(" ")[1]);
            if (indexToMarkDone > taskList.size()) {
                return "OOPS!!! The index you entered is out of bound. \nTo view all the tasks, enter `list`";
            }
            if (indexToMarkDone < 1) {
                return "OOPS!!! The index has to be positive.";
            }
            if (taskList.get(indexToMarkDone - 1).getStatus()) {
                return "This task is already done!";
            }
            taskList.get(indexToMarkDone - 1).markAsDone();
            output = "Nice! I've marked this task as done:\n";
            output += taskList.get(indexToMarkDone - 1).toString() + "\n";
            storage.saveTasksToFile(taskList);
        } catch (NumberFormatException e) {
            return "OOPS!!! The index has to be an integer.";
        }
        return output;
    }

}

