package duke.command;

import java.time.LocalDate;

import duke.logic.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represetns a command telling to list all tasks on a specified date
 */
public class CheckCommand implements Command {
    private LocalDate date;

    /**
     * Constructor
     * @param date Date to check
     */
    public CheckCommand(LocalDate date) {
        this.date = date;
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
            if (this.date.equals(currTask.getDate())) {
                filteredTasks.add(currTask);
            }
        }

        String checkResponse = filteredTasks.toString();
        return checkResponse;
    }
}
