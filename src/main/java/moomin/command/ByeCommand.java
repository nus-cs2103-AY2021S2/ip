package moomin.command;

import moomin.exception.DukeException;
import moomin.storage.Storage;
import moomin.task.TaskList;
import moomin.ui.Ui;

public class ByeCommand extends Command {

    private static final String ERROR_MESSAGE = "Wait, you want to leave or what?";


    public ByeCommand(String details) throws DukeException {
        if (!details.isBlank()) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasksToFile(tasks);
        storage.setMostRecentCommand(this);
        return ui.getFarewellMessage();
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "";
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
