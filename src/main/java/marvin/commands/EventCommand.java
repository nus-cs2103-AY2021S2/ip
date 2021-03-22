package marvin.commands;

import marvin.exception.DukeException;
import marvin.message.Messages;
import marvin.storage.Storage;
import marvin.task.Event;
import marvin.task.Task;
import marvin.task.TaskList;

/**
 * Represents a user command which adds an event to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String description;
    private String date;

    /**
     * Constructor takes in the description of an event and the date which it occurs at.
     * @param description The description of the event.
     * @param date The date which the event occurs at.
     */
    public EventCommand(String description, String date) {
        this.description = description;
        this.date = date;
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
        assert date != null;

        Task newTask = new Event(description, date);
        tasks.add(newTask);
        storage.save(tasks);
        return Messages.getAddTaskMessage(newTask, tasks.size());
    }
}
