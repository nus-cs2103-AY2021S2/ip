package duke.command;

import duke.exception.DescriptionMissingException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * A class represents a TodoCommand.
 */
public class TodoCommand extends AddCommand {
    private final String fullCommand;

    /**
     * Constructs a TodoCommand.
     * @param fullCommand The full command from the user's input.
     */
    public TodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Adds the Todo task into a given task list while updating the file.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @throws DescriptionMissingException If the input is not complete.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DescriptionMissingException {
        Todo todo = getTask();
        super.addThisTask(tasks, todo, ui, storage);
        return ui.addTaskResponse(todo, tasks);
    }

    @Override
    protected Todo getTask() throws DescriptionMissingException {
        String name = fullCommand.substring(4).strip();
        if (name.equals("")) {
            throw new DescriptionMissingException("Argument missing! Please include the name!");
        } else {
            return new Todo(name);
        }
    }
}
