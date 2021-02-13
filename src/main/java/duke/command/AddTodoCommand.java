package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class AddTodoCommand extends Command {

    private static final String addSuccess = "Added to collection.";
    private static final Boolean toExit = false;

    private Todo todo;

    /**
     * Initialises add to-do command by specifying
     * its description.
     *
     * @param desc To-do description.
     */
    public AddTodoCommand(String desc) {
        this.todo = new Todo(desc);
    }

    /**
     * Adds to-do into the task list.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     * @throws DukeException If invalid task type or arguments specified.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.todo);
        return new CommandResponse(AddTodoCommand.addSuccess, AddTodoCommand.toExit);
    }
}
