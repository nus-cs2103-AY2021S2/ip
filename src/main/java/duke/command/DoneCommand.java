package duke.command;

import duke.data.DataStorage;
import duke.exception.DukeException;
import duke.TaskList.TaskList;
import duke.UI.UI;

public class DoneCommand extends Command {

    public DoneCommand(String input, TaskList taskList) {
        super(input, taskList);
    }

    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage) throws DukeException {
        tasks.markAsDone(Integer.parseInt(input)-1);
        storage.save(tasks.getTaskListArray());
        return tasks;
    }
}
