package duke.command;

import duke.DukeException;
import duke.Messages;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;
        assert description != null;

        Task newTask = new Todo(description);
        tasks.add(newTask);
        return Messages.getAddTaskMessage(newTask, tasks.size());
    }
}
