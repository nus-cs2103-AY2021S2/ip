package duke.commands;

import duke.exception.DukeException;
import duke.message.Messages;
import duke.storage.Storage;
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

        Task deletedTask = tasks.remove(index);
        storage.save(tasks);
        return Messages.getDeleteTaskMessage(deletedTask, tasks.size());
    }
}
