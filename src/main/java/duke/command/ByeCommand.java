package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        System.out.println("     Bye. Hope to see you again soon!");
        storage.clear();
        storage.save(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
