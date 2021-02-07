package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteCommandException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String description) {
        super(description);
    }

    /**
     * Gives a goodbye greeting
     *
     * @param taskList, the list of tasks
     * @param ui, the UI object
     * @param storage, the storage object
     * @return String which is a goodbye greeting after saving taskList into storage
     * @throws DukeException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ", 2);

        if (inputList.length > 1) {
            throw new IncompleteCommandException();
        }

        storage.saveTaskList(taskList);
        return ui.goodBye();
    }

    @Override
    public boolean isEndOfProgram() {
        return true;
    }
}
