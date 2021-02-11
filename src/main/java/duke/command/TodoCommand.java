package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "todo";
    private String description;

    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        assert description != null;

        tasks.add(new Todo(description));
        return super.execute(tasks, ui, storage);
    }
}
