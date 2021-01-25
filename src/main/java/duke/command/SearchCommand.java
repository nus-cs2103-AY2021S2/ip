package duke.command;

import duke.TaskList.TaskList;
import duke.UI.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

public class SearchCommand extends Command {

    public SearchCommand(String input, TaskList taskList) {
        super(input.trim(), taskList);
    }

    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage) throws DukeException {
        tasks.findTask(input);
        return tasks;
    }
}
