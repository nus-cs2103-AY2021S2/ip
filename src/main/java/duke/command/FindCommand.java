package duke.command;

import java.util.ArrayList;

import duke.Exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Please enter the keywords!");
        }

        this.keyword = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> newTasks = tasks.findMatchingTasks(this.keyword);
        ui.displayMatchingTasks(newTasks);
        storage.saveTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
