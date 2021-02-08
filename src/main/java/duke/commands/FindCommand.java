package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of searching the to-do list for tasks that matches certain keywords.
 */
public class FindCommand extends Command {
    private final String description;

    /**
     * Initializes a command to search the application's <code>TaskList</code> for task(s)
     * whose descriptions match the input keyword(s).
     *
     * @param description The input keyword(s).
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new <code>TaskList</code> object containing only tasks with descriptions that match
     * the keyword of the "find" command. Then, prints the descriptions and statuses of the
     * <code>Task</code> objects in the new <code>TaskList</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks.getListOfTasks()) {
            String taskDescription = task.getDescription().toLowerCase();
            String stringToFind = this.description.toLowerCase();

            if (taskDescription.contains(stringToFind)) {
                matchingTasks.addTask(task);
            }
        }

        ui.handleFind(matchingTasks, this.description);
    }

    public boolean isExit() {
        return false;
    }
}
