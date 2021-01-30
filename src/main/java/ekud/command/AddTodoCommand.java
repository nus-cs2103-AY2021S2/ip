package ekud.command;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.task.ToDo;

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
     * @param storage The file writer.
     * @return Summary of the task added.
     * @throws EkudException If task saving fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EkudException {
        tasks.add(new ToDo(description));
        return super.execute(tasks, storage);
    }
}
