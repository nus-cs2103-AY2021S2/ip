package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) throws DukeException {
        if (keyword.trim().isEmpty()) {
            throw new DukeException("Please enter a keyword!");
        }
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList result = tasks.find(keyword);
        return ui.printTaskList(result);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
