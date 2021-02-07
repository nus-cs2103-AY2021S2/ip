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
 * A class represents an EventCommand.
 */
public class EventCommand extends AddCommand {
    private final String[] descriptions;

    /**
     * Constructs an EventCommand.
     * @param descriptions The full command from user's input.
     */
    public EventCommand(String[] descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Adds the Event task into a given task list while updating the file.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @throws DukeException If error occurs during the process.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert(descriptions.length != 0);
        String name = descriptions[0];
        if (name.equals("")) {
            throw new DescriptionMissingException("Please include the name!");
        }
        if (descriptions.length < 2) {
            throw new DescriptionMissingException("Please include the date and time!");
        }
        Task event = Parser.parseTask(EnumTask.EVENT, name, descriptions[1]);
        super.addThisTask(tasks, event, ui, storage);
        return ui.addTaskResponse(event, tasks);
    }
}
