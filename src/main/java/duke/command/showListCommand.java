package duke.command;

import java.util.Comparator;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class showListCommand extends Command {

    public showListCommand(String description) {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getNumOfTasks() == 0) {
            throw new DukeException("There are currently no duke.tasks in your list.");
        }
        Comparator<Task> byDeadline = new Comparator<>() {
            public int compare(Task t1, Task t2) {
                return t1.getLocalDateTime().compareTo(t2.getLocalDateTime());
            }
        };
        tasks.getTasks().sort(byDeadline);
        String result;
        result = ui.displayList(tasks.getTasks());
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
