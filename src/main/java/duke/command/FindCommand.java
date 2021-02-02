package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Please enter the keywords!");
        }

        this.keyword = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> newTasks = tasks.findMatchingTasks(this.keyword);
        String result = ui.displayMatchingTasks(newTasks);
        storage.saveTasks(tasks.getTasks());
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
