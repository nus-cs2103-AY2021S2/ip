package duke.commands;

import duke.exception.DukeException;
import duke.message.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        Task doneTask = tasks.get(index);
        doneTask.setDone(true);
        storage.save(tasks);
        return Messages.getDoneTaskMessage(doneTask);
    }
}
