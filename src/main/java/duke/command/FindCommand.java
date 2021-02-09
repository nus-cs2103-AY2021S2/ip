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
        super.commandDetail = commandDetails;
        super.dateTime = "";
        super.outputMessage = "";
        // index is -1 because it is only used in done and delete tasks
        super.index = -1;
    }

    private void retrieveMatchingTasks(TaskList taskList) {
        StringBuilder currText = new StringBuilder("Here are the matching tasks in your list:");
        int index = 0;

        for (int num = 1; num <= taskList.getSize(); num++) {
            Task currentTask = taskList.getTask(num - 1);
            String description = currentTask.getDescription();
            boolean isValidKeyword = findKeyword(description, this.commandDetail);

            if (isValidKeyword) {
                index++;
                String taskDetail = currentTask.toString();
                currText.append("\n\t ").append(index).append(".").append(taskDetail);
            }
        }
        this.outputMessage = currText.toString();
    }

    private boolean findKeyword(String string, String subString) {
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
