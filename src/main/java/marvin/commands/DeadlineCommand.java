package marvin.commands;

import marvin.exception.DukeException;
import marvin.message.Messages;
import marvin.storage.Storage;
import marvin.task.Deadline;
import marvin.task.Task;
import marvin.task.TaskList;

/**
 * Represents a user command which adds a deadline to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String dateTime;

    /**
     * Constructor takes in the description of a deadline and
     * the date and time it is due by.
     * @param description The description of the deadline.
     * @param dateTime The date and time the description is due by.
     */
    public DeadlineCommand(String description, String dateTime) {
        super();
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Executes the user command and returns a response message
     * @param tasks The state of the current task list.
     * @param storage The storage used by the program.
     * @return Response message.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;
        assert description != null;
        assert dateTime != null;

        Task newTask = new Deadline(description, dateTime);
        tasks.add(newTask);
        storage.save(tasks);
        return Messages.getAddTaskMessage(newTask, tasks.size());
    }
}
