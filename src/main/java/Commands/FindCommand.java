package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class FindCommand extends Command {
    private final String word;

    public FindCommand(String word) {
        super();
        this.word = word;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.find(tasklist.find(this.word));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
