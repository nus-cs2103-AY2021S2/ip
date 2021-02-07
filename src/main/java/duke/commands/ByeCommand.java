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
