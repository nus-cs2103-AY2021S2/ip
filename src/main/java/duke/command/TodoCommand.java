package duke.command;

import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.EnumTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a TodoCommand.
 */
public class TodoCommand extends AddCommand {
    private final String description;

    /**
     * Constructs a TodoCommand.
     * @param description The full command from the user's input.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds the Todo task into a given task list while updating the file.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @throws DescriptionMissingException If the input is not complete.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String name = description;
        if (name.equals("")) {
            throw new DescriptionMissingException("Please include the name!");
        }
        Task todo = Parser.parseTask(EnumTask.TODO, description);
        super.addThisTask(tasks, todo, ui, storage);
        return ui.addTaskResponse(todo, tasks);
    }
}
