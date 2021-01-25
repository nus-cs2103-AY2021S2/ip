package ekud.command;

import ekud.common.exception.*;
import ekud.storage.*;
import ekud.task.*;
import ekud.ui.*;

public class DoneCommand extends ModificationCommand {
    protected int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Mark the task at index as done
     *
     * @param tasks   the list of tasks
     * @param ui      the user interface
     * @param storage the file writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task doneTask = tasks.markDone(index);
        super.execute(tasks, ui, storage);

        ui.printLines(
                "Good job! The task below is marked done!",
                "\t" + doneTask.toString());
    }
}
