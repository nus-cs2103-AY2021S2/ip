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
 * A class represents an EventCommand.
 */
public class EventCommand extends AddCommand {
    /**
     * Constructs an EventCommand.
     * @param descriptions The descriptions that contains name and time of the event.
     */
    public EventCommand(TaskDescription descriptions) {
        super(descriptions);
    }

    /**
     * Adds the Event task into a given task list while updating the file.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @return The message of adding an Event task.
     * @throws DukeException If error occurs during the process.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String name = descriptions.getDescriptionOfIndex(NAME_INDEX).trim();
            String dateAndTime = descriptions.getDescriptionOfIndex(DATE_TIME_INDEX);
            Task event = TaskParser.parseTask(EnumTask.EVENT, name, dateAndTime);
            super.addThisTask(tasks, event, ui, storage);
            return ui.addTaskResponse(event, tasks);
        } catch (DescriptionMissingException e) {
            throw new DescriptionMissingException(e.getMessage() + "Please specify the name!");
        } catch (DescriptionIndexOutOfBoundsException e) {
            throw new DescriptionIndexOutOfBoundsException(e.getMessage() + "Please include the date and time!");
        }
    }
}
