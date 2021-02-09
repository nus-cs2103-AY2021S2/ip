package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * ListCommand handles the retrieval of all the tasks in the TaskList as requested by the user
 */
public class ListCommand extends Command {

    /**
     * ListCommand Constructor
     *
     * @param commandType Task name
     */
    public ListCommand(String commandType) {
        super.commandType = commandType;
        super.commandDetail = "";
        super.dateTime = "";
        super.outputMessage = "";
        // index is -1 because it is only used in done and delete tasks
        super.index = -1;
    }

    private void retrieveList(TaskList taskList) {
        StringBuilder currText = new StringBuilder("Here are the tasks in your list:");

        for (int num = 1; num <= taskList.getSize(); num++) {
            Task currentTask = taskList.getTask(num - 1);
            String taskDetail = currentTask.toString();
            currText.append("\n\t ").append(num).append(".").append(taskDetail);
        }
        this.outputMessage = currText.toString();
    }

    /**
     * Retrieve all the tasks from the TaskList given a keyword input from the user and
     * Output the whole TaskList as message to the command line
     *
     * @param tasks TaskList
     * @param storage Instance of Storage
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        retrieveList(tasks);
    }
}
