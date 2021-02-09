package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends AddTaskCommand {
    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Todo(arguments));
        return super.execute(tasks, ui, storage);
    }
}
