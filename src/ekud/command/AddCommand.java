package ekud.command;

import ekud.common.exception.*;
import ekud.storage.*;
import ekud.task.*;
import ekud.ui.*;

public abstract class AddCommand extends ModificationCommand {
    protected String description;

    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Save all tasks to disk
     *
     * @param tasks   the list of tasks
     * @param ui      the user interface
     * @param storage the file writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        ui.printLines("Okay I remember for you liao:",
                "\t" + tasks.last(),
                getTasksLeftString(tasks));
    }
}
