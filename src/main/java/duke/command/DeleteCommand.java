package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private static String INDEX_OUT_OF_BOUND_MESSAGE = "OOPS!!! The index you entered is out of bound. "
            + "\nTo view all the tasks, enter `list`";
    private static String NOT_POSITIVE_INTEGER_MESSAGE = "OOPS!!! The index has to be a positive integer.";
    private static String SUCCESSFUL_DELETE_MESSAGE = "Noted! I have removed this task:\n";

    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the delete command.
     * Deletes the specified task.
     *
     * @param tasks a list of tasks.
     * @param storage the storage of the Duke object.
     *
     * @return the output to be displayed to user.
     *
     * @throws IOException If an input or output exception occurred
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        ArrayList<Task> taskList = tasks.getTasks();
        int taskCount = taskList.size();
        String output = "";
        try {
            int indexToMarkDelete = Integer.parseInt(fullCommand.split(" ")[1]);
            if (indexToMarkDelete > taskList.size()) {
                return INDEX_OUT_OF_BOUND_MESSAGE;
            }
            if (indexToMarkDelete < 1) {
                return NOT_POSITIVE_INTEGER_MESSAGE;
            }
            String taskDeleted = taskList.get(indexToMarkDelete - 1).toString() + "\n";
            taskList.remove(indexToMarkDelete - 1);
            output += SUCCESSFUL_DELETE_MESSAGE;
            output += taskDeleted;
            output += String.format("Now you have %d tasks in the list.\n", taskCount - 1);
            storage.saveTasksToFile(taskList);
        } catch (NumberFormatException e) {
            return NOT_POSITIVE_INTEGER_MESSAGE;
        }
        return output;
    }
}
