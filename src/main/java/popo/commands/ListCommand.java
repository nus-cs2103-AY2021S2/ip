package popo.commands;

import static popo.utils.Messages.MESSAGE_EMPTY_TASKLIST;
import static popo.utils.Messages.MESSAGE_INDEX_TASK_FORMAT;
import static popo.utils.Messages.MESSAGE_SHOW_TASKLIST;

/**
 * Lists the current tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Print the list of current tasks\n"
            + "Usage: list";

    @Override
    public CommandResult execute() {
        assert taskList != null;
        if (taskList.isEmpty()) {
            return new CommandResult(false, MESSAGE_EMPTY_TASKLIST);
        }
        String taskListAsString = getTaskListAsString();
        String trimmedTaskListDescription = taskListAsString.trim();
        return new CommandResult(false,
                MESSAGE_SHOW_TASKLIST + "\n",
                trimmedTaskListDescription);
    }

    private String getTaskListAsString() {
        StringBuilder taskListAsString = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            taskListAsString.append(String.format(MESSAGE_INDEX_TASK_FORMAT, i, taskList.getTask(i - 1)));
            taskListAsString.append("\n");
        }
        return taskListAsString.toString();
    }
}
