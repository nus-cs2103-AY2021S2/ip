package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Event;

import java.time.LocalDateTime;

/**
 * Represents the command to add an Event task.
 */
public class AddEventCommand extends Command {

    private static final boolean toExit = false;

    private Event event;

    /**
     * Constructs a AddEventCommand object.
     *
     * @param description Event description.
     * @param dateTime Event DateTime object.
     */
    public AddEventCommand(String description, LocalDateTime dateTime) {
        this.event = new Event(description, dateTime);
    }

    /**
     * Executes the command and returns a response.
     *
     * @param tasks Core TaskList object.
     * @param storage Core Storage object.
     * @return CommandResponse object.
     * @throws QuackersException If unable to save data to disk.
     */
    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) throws QuackersException {
        tasks.addTask(this.event);
        storage.save(tasks);

        String message = Ui.getAddTaskSuccess(this.event);
        return new CommandResponse(AddEventCommand.class, message, AddEventCommand.toExit);
    }
}
