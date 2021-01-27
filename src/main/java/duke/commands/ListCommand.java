package duke.commands;

/**
 * Lists the current tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    private static final String MESSAGE_EMPTY_TASKLIST = "You do not have anything to do at the moment!";
    private static final String MESSAGE_SHOW_TASKLIST = "Here are the tasks in your list:";
    private static final String MESSAGE_INDEX_TASK_FORMAT = "%d.%s";

    @Override
    public CommandResult execute() {
        if (taskList.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_TASKLIST);
        } else {
            StringBuilder tasksListAsString = new StringBuilder();
            for (int i = 1; i <= taskList.size(); i++) {
                tasksListAsString.append(String.format(MESSAGE_INDEX_TASK_FORMAT, i, taskList.getTask(i - 1)));
                if (i != taskList.size()) {
                    tasksListAsString.append("\n");
                }
            }
            return new CommandResult(MESSAGE_SHOW_TASKLIST + "\n" + tasksListAsString.toString());
        }
    }
}
