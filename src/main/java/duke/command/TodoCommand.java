package duke.command;

import duke.exception.DescriptionIndexOutOfBoundsException;
import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.parser.TaskParser;
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
     * @param descriptions The descriptions which contains name for the TodoCommand.
     */
    public TodoCommand(TaskDescription descriptions) {
        super(descriptions);
    }
    /**
     * Adds the Todo task into a given task list while updating the file.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @return The message of adding a Todo task.
     * @throws DukeException If error occurs during the process.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String name = descriptions.getDescriptionOfIndex(NAME_INDEX).trim();
            Task todo = TaskParser.parseTask(EnumTask.TODO, name);
            super.addThisTask(tasks, todo, ui, storage);
            return ui.addTaskResponse(todo, tasks);
        } catch (DescriptionMissingException e) {
            throw new DescriptionMissingException(e.getMessage() + "Please specify the name!");
        } catch (DescriptionIndexOutOfBoundsException e) {
            throw new DescriptionIndexOutOfBoundsException(e.getMessage());
        }
    }
}
