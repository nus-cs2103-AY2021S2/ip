package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.TaskStringFormatter;

/**
 * Handles the logic of searching the to-do list for tasks that matches certain keywords.
 */
public class FindCommand extends Command {
    private final String keywords;

    /**
     * Initializes a command to search the application's <code>TaskList</code> for task(s)
     * whose descriptions match the input keyword(s).
     *
     * @param keywords The input keyword(s).
     */
    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns false as searching for tasks should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Does nothing.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
    }

    /**
     * Creates a response to display the search results to the users. The tasks in the search
     * results will take the form of a multi-line string, with each row displaying one task with its
     * index, description and status.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A <code>String</code> displaying the search results.
     */
    public String getResponse(TaskList tasks) {
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks.getListOfTasks()) {
            String taskDescription = task.getDescription().toLowerCase();
            String stringToFind = this.keywords.toLowerCase();

            if (taskDescription.contains(stringToFind)) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.getSize() == 0) {
            return "No tasks matches the keyword(s) '" + this.keywords + "' :O";
        } else {
            return "Meow I've found the matching tasks:\n"
                    + "\n"
                    + TaskStringFormatter.getTaskTable(matchingTasks);
        }
    }
}
