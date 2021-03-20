package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.javafx.binding.StringFormatter;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Storage storage) throws IOException {
        ArrayList<Task> taskList = tasks.getTasks();
        int taskCount = taskList.size();
        String output = "";
        try {
            int indexToMarkDelete = Integer.parseInt(fullCommand.split(" ")[1]);
            if (indexToMarkDelete > taskList.size()) {
                return "OOPS!!! The index you entered is out of bound. \nTo view all the tasks, enter `list`";
            }
            if (indexToMarkDelete < 1) {
                return "OOPS!!! The index has to be positive.";
            }
            String taskDeleted = taskList.get(indexToMarkDelete - 1).toString() + "\n";
            taskList.remove(indexToMarkDelete - 1);
            output += "Noted! I have removed this task:\n";
            output += taskDeleted;
            output += String.format("Now you have %d tasks in the list.\n", taskCount - 1);
            storage.saveTasksToFile(taskList);
        } catch (NumberFormatException e) {
            return "OOPS!!! The index has to be an integer.";
        }
        return output;
    }
}
