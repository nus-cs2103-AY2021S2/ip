package dbot.command;

import dbot.exception.DBotException;
import dbot.storage.Storage;
import dbot.task.Task;
import dbot.tasklist.TaskList;
import dbot.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Initializes a Find Command to search for the given keyword.
     *
     * @param keyword A String to be matched against the description of Tasks in the TaskList.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DBotException {
        TaskList relevantTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                relevantTasks.add(task);
            }
        }
        ui.printRelevantTasks(relevantTasks);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) throws DBotException {
        throw new IllegalArgumentException("The 'find' command cannot be used quietly.");
    }
}
