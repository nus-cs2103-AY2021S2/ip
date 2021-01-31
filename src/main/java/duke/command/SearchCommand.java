package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

public class SearchCommand extends Command {

    public SearchCommand(String input) {
        super(input.trim());
    }

    @Override
    public String execute() throws DukeException {
        return tasklist.findTask(input, this.tasklist);
    }
}
