package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Deadline;

import java.time.LocalDateTime;

/**
 * Represents the command to add a Deadline task.
 */
public class AddDeadlineCommand extends Command {

    private static final boolean toExit = false;

    private Deadline deadline;

    /**
     * Constructs a AddDeadlineCommand object.
     *
     * @param description Deadline description.
     * @param dateTime Deadline DateTime object.
     */
    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        this.deadline = new Deadline(description, dateTime);
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
        tasks.addTask(this.deadline);
        storage.save(tasks);

        String message = Ui.getAddTaskSuccess(this.deadline);
        return new CommandResponse(AddDeadlineCommand.class, message, AddDeadlineCommand.toExit);
    }
}
