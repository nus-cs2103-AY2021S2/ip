package duke.command;

import java.time.LocalDateTime;

import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a DeadlineCommand.
 */
public class DeadlineCommand extends AddCommand {
    private final String fullCommand;

    /**
     * Constructs a DeadlineCommand.
     * @param fullCommand Full command from the user's input.
     */
    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
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
        Deadline deadline = getTask();
        super.addThisTask(tasks, deadline, storage);
        return ui.addTaskResponse(deadline, tasks);
    }

    @Override
    protected Deadline getTask()
            throws DescriptionMissingException, InvalidDateTimeException {
        String nameDeadline = fullCommand.substring(8).strip();
        if (nameDeadline.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        }

        String[] nameAndDeadline = nameDeadline.split("/by");
        if (nameAndDeadline.length < 2) {
            throw new DescriptionMissingException("Argument missing!");
        }
        String name = nameAndDeadline[0].strip();
        String deadline = nameAndDeadline[1].strip();

        LocalDateTime cutOffTime = Parser.parseDateTime(deadline);
        return new Deadline(name, cutOffTime);
    }
}
