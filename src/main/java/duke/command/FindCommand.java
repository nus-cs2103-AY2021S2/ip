package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(Ui ui, TaskManager tm, Storage st) throws DukeException {
        ui.println("Listing all matching tasks: ");
        ui.showTasks(tm.findTasks(this.searchTerm));
    }
}
