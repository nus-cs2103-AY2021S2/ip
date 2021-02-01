package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * FindCommand handles the filtering of tasks in the list that matches the keyword given by the user only
 */
public class FindCommand extends Command {

    /**
     * FindCommand Constructor
     *
     * @param commandType Task name
     * @param commandDetails Task details
     */
    public FindCommand(String commandType, String commandDetails) {
        super.commandType = commandType;
        super.commandDetails = commandDetails;
        super.dateTime = "";
        super.outputMessage = "";
        // index is -1 because it is only used in done and delete tasks
        super.index = -1;
    }

    private void retrieveMatchingTasks(TaskList taskList) {
        StringBuilder currText = new StringBuilder("Here are the matching tasks in your list:");
        int index = 0;

        for (int num = 1; num <= taskList.size(); num++) {
            Task currentTask = taskList.get(num - 1);
            String description = currentTask.getDescription();

            if (ignoreCase(description, this.commandDetails)) {
                index++;
                currText.append("\n\t ").append(index).append(".").append(currentTask.toString());
            }
        }
        this.outputMessage = currText.toString();
    }

    private boolean ignoreCase(String string, String subString) {
        return string.toLowerCase().contains(subString.toLowerCase());
    }

    /**
     * Find a subset of tasks from the TaskList given a keyword input from the user and
     * Display the result as output message in the command line
     *
     * @param tasks TaskList
     * @param storage Instance of Storage
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        retrieveMatchingTasks(tasks);
    }
}
