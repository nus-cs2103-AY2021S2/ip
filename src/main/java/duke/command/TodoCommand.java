package duke.command;

import duke.exception.DescriptionIndexOutOfBoundsException;
import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.EnumTask;
import duke.task.Task;
import duke.task.TaskDescription;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a TodoCommand.
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs a TodoCommand.
     * @param descriptions The full command from the user's input.
     */
    public TodoCommand(TaskDescription descriptions) {
        super(descriptions);
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
        try {
            String name = descriptions.getDescriptionOfIndex(NAME_INDEX);
            Task todo = Parser.parseTask(EnumTask.TODO, name);
            super.addThisTask(tasks, todo, ui, storage);
            return ui.addTaskResponse(todo, tasks);
        } catch (DescriptionMissingException e) {
            throw new DescriptionMissingException(e.getMessage() + "Please specify the name!");
        } catch (DescriptionIndexOutOfBoundsException e) {
            throw new DescriptionIndexOutOfBoundsException(e.getMessage());
        }
    }
}
