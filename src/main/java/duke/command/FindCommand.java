package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the find command.
     * Displays all the tasks with contains the name input from the user.
     *
     * @param tasks a list of tasks.
     * @param storage the storage of the Duke object.
     *
     * @return the output to be displayed to user.
     */
    public String execute(TaskList tasks, Storage storage) {
        if (fullCommand.equals("find")) {
            return "OOPS!!! The task name is missing.";
        }
        int indexOfSpace = fullCommand.indexOf(" ");
        String taskToLookFor = fullCommand.substring(indexOfSpace).trim();
        ArrayList<Task> taskList = tasks.getTasks();
        int matchCount = 0;
        String matchedTasks = "";
        for (Task task : taskList) {
            if (task.getName().contains(taskToLookFor)) {
                matchCount++;
                matchedTasks += String.format("%d. %s\n", matchCount, task.toString());
            }
        }
        if (matchCount == 0) {
            return "OOPS!!! There is no matching task in your list";
        } else {
            String output = "Here are the matching tasks in your list: \n";
            return output + matchedTasks;
        }
    }
}
