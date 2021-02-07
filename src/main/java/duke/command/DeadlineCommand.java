package duke.command;

import java.time.LocalDateTime;

import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.EnumTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a DeadlineCommand.
 */
public class DeadlineCommand extends AddCommand {
    private final String[] descriptions;

    /**
     * Constructs a DeadlineCommand.
     * @param descriptions Full command from the user's input.
     */
    public DeadlineCommand(String[] descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Adds the Deadline task into a given task list while updating the file.
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
        Task deadline = Parser.parseTask(EnumTask.DEADLINE, name, descriptions[1]);
        super.addThisTask(tasks, deadline, ui, storage);
        return ui.addTaskResponse(deadline, tasks);
    }
}
