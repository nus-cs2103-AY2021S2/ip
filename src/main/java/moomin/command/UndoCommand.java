package moomin.command;

import moomin.exception.DukeException;
import moomin.storage.Storage;
import moomin.task.TaskList;
import moomin.ui.Ui;

public class UndoCommand extends Command {

    private static final String ERROR_MESSAGE = "Wait, you want to undo or what?";


    public UndoCommand(String details) throws DukeException {
        if (!details.isBlank()) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Command mostRecentCommand = storage.getMostRecentCommand();
        if (mostRecentCommand == null) {
            storage.setMostRecentCommand(this);
            return ui.getNoUndoMessage();
        }
        storage.setMostRecentCommand(this);
        return mostRecentCommand.undo(tasks, ui, storage);
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.getUndoUndoMessage();
    }


    @Override
    public boolean isExitCommand() {
        return false;
    }
}
