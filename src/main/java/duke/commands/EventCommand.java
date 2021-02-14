package duke.commands;

import static duke.commands.CommandUtils.checkDateTimeConflicts;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.ParserUtils;
import duke.TaskList;
import duke.models.Event;
import duke.models.Task;

/**
 * Handles the Event command of the user to create new events in the list.
 * Format of command: "event [event_name] /at [datetime]".
 */
public class EventCommand extends AddCommand {
    private final LocalDateTime dateTime;

    /**
     * Command relating to creating a new Event
     *
     * @param taskName name of the event
     * @param dateTime date and time of the event
     */
    protected EventCommand(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    @Override
    public Task getTask() {
        return new Event(getTaskName(), dateTime);
    }

    @Override
    public boolean isTaskValid(Task task, TaskList tasks) throws DukeException {
        assert (task instanceof Event);
        boolean isConflict = checkDateTimeConflicts(((Event) task).getDate(), tasks);
        if (isConflict) {
            throw new DukeException("You have another deadline / event ongoing at the given datetime.");
        }
        return true;
    }

    /**
     * Creates a new instance of Event Command
     *
     * @param argString string with argument
     * @return instance of Event Command
     * @throws DukeException
     */
    public static EventCommand buildInstance(String argString) throws DukeException {
        String[] cmdArgs = ParserUtils.getCommandArgs(argString, "The description of an event cannot be empty.");

        assert (cmdArgs[0].equals("event"));

        String[] eventArgs = cmdArgs[1].split(" /at ", 2);
        if (eventArgs.length < 2) {
            throw new DukeException("The event needs to have a date specified with \"/at\".");
        }
        String taskName = eventArgs[0];
        LocalDateTime dateTime = ParserUtils.parseDateTime(eventArgs[1],
            "The event date needs to be specified in a valid date format.");
        return new EventCommand(taskName, dateTime);
    }
}
