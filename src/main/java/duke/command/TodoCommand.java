package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Todo;
import duke.exception.DescriptionMissingException;

/**
 * A class represents a TodoCommand.
 */
public class TodoCommand extends AddCommand {
    String fullCommand;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DescriptionMissingException {
        Todo todo = getTask();
        super.addThisTask(tasks, todo, storage);
    }

    @Override
    protected Todo getTask() throws DescriptionMissingException {
        String name = fullCommand.substring(4).strip();
        if (name.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        } else {
            return new Todo(name);
        }
    }
}
