package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        if (0 <= index && index < tasks.size()) {
            final Task deletedTask = tasks.delete(index);
            boolean hasOnlyOneTask = tasks.size() == 1;
            return ui.getDeleteTaskMessage(deletedTask, hasOnlyOneTask);
        } else {
            return ui.getIndexOutOfBoundMessage();
        }
    }

}
