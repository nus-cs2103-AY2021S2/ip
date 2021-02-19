package duke.command;

import duke.logic.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command telling Duke to find tasks containing a given word
 */
public class FindCommand implements Command {
    private String searchString;

    /**
     * Constructor
     * @param searchString The user input
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList filteredTasks = new TaskList();

        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDetail().indexOf(this.searchString) >= 0) {
                filteredTasks.add(currTask);
            }
        }

        String findResponse = filteredTasks.toString();
        return findResponse;
    }
}
