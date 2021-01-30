package commands;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private final String word;

    /**
     * Constructor for the Find Command.
     * @param word String that DukeTask contains in the TaskList.
     */
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
