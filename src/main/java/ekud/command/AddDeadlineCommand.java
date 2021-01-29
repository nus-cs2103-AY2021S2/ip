package ekud.command;

import java.time.LocalDateTime;

import ekud.common.exception.DukeException;
import ekud.storage.Storage;
import ekud.task.Deadline;
import ekud.task.TaskList;
import ekud.ui.Ui;

/**
 * Command that creates a deadline task.
 */
public class AddDeadlineCommand extends AddTimedTaskCommand {
    /**
     * Construct a deadline-task-creating command.
     *
     * @param description The description of the deadline.
     * @param dateTime    The date and time of the deadline.
     */
    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    /**
     * Execute this task by adding a deadline into the list, followed by common procedures of all add commands.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The file writer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Deadline(description, dateTime));
        super.execute(tasks, ui, storage);
    }
}
