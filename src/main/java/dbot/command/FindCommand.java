package dbot.command;

import dbot.exception.DukeException;
import dbot.storage.Storage;
import dbot.task.Task;
import dbot.tasklist.TaskList;
import dbot.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList relevantTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                relevantTasks.add(task);
            }
        }
        ui.printRelevantTasks(relevantTasks);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) throws DukeException {

    }
}
