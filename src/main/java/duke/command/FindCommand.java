package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Class representing the Find Command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor of FindCommand class.
     *
     * @param keyword The keyword entered by the user.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the Find command.
     * Searches through all tasks and checks whether task descriptions contain the keyword.
     * If task description contains the keyword, corresponding task is printed.
     *
     * @param tasks List of tasks.
     * @param ui Formats and prints the list of matching tasks to user.
     * @param storage
     * @return true.
     */
    public String execute(TaskList tasks, Ui ui, TaskStorage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        ListIterator<Task> iterator = tasks.getIterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return ui.formatFindCmdMsg(new TaskList(matchingTasks));
    }
}
