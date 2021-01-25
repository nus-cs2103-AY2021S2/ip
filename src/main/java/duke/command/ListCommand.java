package duke.command;

import duke.Ui;
import duke.DukeException;
import duke.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void process() throws DukeException {
        Ui.displayAllTasks(Storage.getTasks());
    }
}
