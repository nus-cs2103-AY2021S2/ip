package duke.commands;

import duke.exception.DukeException;
import duke.message.Messages;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a user command which adds an event to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String description;
    private String dateTime;

    /**
     * Constructor takes in the description of an event and the date and time which it occurs at.
     * @param description The description of the event.
     * @param dateTime The date and time which the event occurs at.
     */
    public EventCommand(String description, String dateTime) {
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

        Task newTask = new Event(description, dateTime);
        tasks.add(newTask);
        storage.save(tasks);
        return Messages.getAddTaskMessage(newTask, tasks.size());
    }
}
