package duke.command;

import duke.DukeException;
import duke.Messages;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        Task deletedTask = tasks.remove(index - 1);
        storage.saveTasksToFile(tasks);
        return Messages.getDeleteTaskMessage(deletedTask, tasks.size());
    }
}
