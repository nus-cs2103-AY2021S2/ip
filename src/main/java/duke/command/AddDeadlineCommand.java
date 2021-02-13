package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {

    private static final String addSuccess = "Added to collection.";
    private static final Boolean toExit = false;

    private Deadline deadline;

    /**
     * Initialises add deadline command by specifying
     * its description and deadline date time.
     *
     * @param desc Deadline description.
     * @param dateTime Deadline date time.
     */
    public AddDeadlineCommand(String desc, LocalDateTime dateTime) {
        this.deadline = new Deadline(desc, dateTime);
    }

    /**
     * Adds deadline into the task list.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     * @throws DukeException If invalid task type or arguments specified.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.deadline);
        return new CommandResponse(AddDeadlineCommand.addSuccess, AddDeadlineCommand.toExit);
    }
}
