package ekud.command;

import java.time.LocalDateTime;

import ekud.common.exception.EkudException;
import ekud.storage.Storage;
import ekud.task.EventTask;
import ekud.task.TaskList;

/**
 * Command that creates an event.
 */
public class AddEventCommand extends AddTimedTaskCommand {
    /**
     * Construct a event-task-creating command.
     *
     * @param description The description of the event.
     * @param dateTime    The date and time of the event.
     */
    public AddEventCommand(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    /**
     * Execute this task by adding a deadline into the list, followed by common procedures of all add commands.
     *
     * @param tasks   The list of tasks.
     * @param storage The file writer.
     * @return Summary of the task added.
     * @throws EkudException If task saving fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EkudException {
        tasks.add(new EventTask(description, dateTime));
        return super.execute(tasks, storage);
    }
}
