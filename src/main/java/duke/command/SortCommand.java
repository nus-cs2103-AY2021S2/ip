package duke.command;

import java.util.Map;
import java.util.TreeMap;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * SortCommand handles the date organisations of task types Deadline and Event in the TaskList
 */
public class SortCommand extends Command {

    /**
     * SortCommand Constructor
     *
     * @param commandType Task name
     */
    public SortCommand(String commandType) {
        super.commandType = commandType;
        super.commandDetail = "";
        super.dateTime = "";
        super.outputMessage = "";
        // index is -1 because it is only used in done and delete tasks
        super.index = -1;
    }

    private void sort(TaskList taskList) {
        StringBuilder currText = new StringBuilder("Here are the events and deadline "
                + "tasks (sorted by date) in your list:");
        TreeMap<String, String> map = new TreeMap<>();

        for (int num = 1; num <= taskList.getSize(); num++) {
            Task currentTask = taskList.getTask(num - 1);
            String taskDetail = currentTask.toString();
            String taskType = taskDetail.substring(1, 2);

            if (!(taskType.equals("E") || taskType.equals("D"))) {
                continue;
            }
            map.put(formatDateString(taskType, taskDetail), taskDetail);
        }

        int index = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            index++;
            String taskDetail = entry.getValue();
            currText.append("\n\t ").append(index).append(".").append(taskDetail);
        }
        this.outputMessage = currText.toString();
    }

    private String formatDateString(String taskType, String description) {
        String[] result = new String[2];

        if (taskType.equals("E")) { // Guard clause
            result = description.split("at: ");
        }

        if (taskType.equals("D")) { // Guard clause
            result = description.split("by: ");
        }

        String[] dateTime = result[1].replace(")", "").split("-");
        String dateTimeInt = String.join("", dateTime[2] + dateTime[1] + dateTime[0]);
        return dateTimeInt;
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
        sort(tasks);
    }
}
