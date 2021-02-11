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
        tasks.add(new Todo(description));
        return super.execute(tasks, ui, storage);
    }
}
