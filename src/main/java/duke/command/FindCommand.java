package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import duke.task.Task;

import duke.util.MessageFormatter;
import duke.util.TaskList;
import duke.util.TaskStorage;

/**
 * Class representing the Find Command.
 */
public class FindCommand extends Command {
    private final String keyword;

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
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Storage of tasks.
     * @return A list of tasks with descriptions that matches the keyword entered by the user.
     */
    public String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        ListIterator<Task> iterator = tasks.getIterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return messageFormatter.formatFindMsg(new TaskList(matchingTasks));
    }
}
