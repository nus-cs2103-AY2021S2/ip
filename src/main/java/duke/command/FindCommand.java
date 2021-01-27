package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

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
    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        List<Task> temp = new ArrayList<>();
        TaskList allTasks = storage.retrieveData();
        for (int i = 0; i < allTasks.getSize(); i++) {
            Task task = allTasks.getTask(i);
            String description = task.getDescription();
            if (description.contains(keyword)) {
                temp.add(task);
            }
        }
        if (temp.isEmpty()) {
            ui.print("There are no tasks with such keyword!");
        } else {
            TaskList matchingTasks = new TaskList(temp);
            ui.print(matchingTasks);
        }
        return true;
    }
}
