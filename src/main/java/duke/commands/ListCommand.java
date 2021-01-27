package duke.commands;

/**
 * Lists the current tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String EMPTY_TASKLIST_MESSAGE = "You do not have anything to do at the moment!";
    private static final String SHOWING_TASKLIST_MESSAGE = "Here are the tasks in your list:\n";
    private static final String INDEXED_TASK_ITEM_FORMAT = "%d.%s";

    public ListCommand() {
    }

    @Override
    public CommandResult execute() {
        if (taskList.isEmpty()) {
            return new CommandResult(EMPTY_TASKLIST_MESSAGE);
        } else {
            StringBuilder tasksListAsString = new StringBuilder();
            for (int i = 1; i <= taskList.size(); i++) {
                tasksListAsString.append(String.format(INDEXED_TASK_ITEM_FORMAT, i, taskList.getTask(i - 1)));
                if (i == taskList.size()) {
                    break;
                }
                tasksListAsString.append("\n");
            }
            return new CommandResult(SHOWING_TASKLIST_MESSAGE + tasksListAsString.toString());
        }
    }
}
