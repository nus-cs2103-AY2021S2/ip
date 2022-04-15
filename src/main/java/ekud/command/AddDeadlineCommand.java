package ekud.command;

import java.time.LocalDateTime;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.Deadline;
import ekud.task.TaskList;

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
     * @param storage The file writer.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EkudException {
        tasks.add(new Deadline(description, dateTime));
        return super.execute(tasks, storage);
    }
}
