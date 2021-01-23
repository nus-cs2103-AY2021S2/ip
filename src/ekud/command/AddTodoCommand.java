package ekud.command;

import ekud.common.exception.DukeException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.task.ToDo;
import ekud.ui.Ui;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String description) {
        super(description);
    }

    /**
     * Execute this command by adding a todo into the list, followed by common procedures of all add commands
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the file writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new ToDo(description));
        super.execute(tasks, ui, storage);
    }
}
