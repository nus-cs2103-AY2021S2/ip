package ekud.command;

import ekud.common.exception.DukeException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.task.ToDo;
import ekud.ui.Ui;

/**
 * Command that creates a todo task.
 */
public class AddTodoCommand extends AddCommand {
    /**
     * Construct and todo-task-creating command.
     *
     * @param description The description of the todo.
     */
    public AddTodoCommand(String description) {
        super(description);
    }

    /**
     * Execute this command by adding a todo into the list, followed by common procedures of all add commands.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The file writer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new ToDo(description));
        super.execute(tasks, ui, storage);
    }
}
