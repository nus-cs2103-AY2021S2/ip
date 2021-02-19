package command;

import task.Event;
import task.TaskManager;
import util.DukeException;
import util.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Command to add an Event Task to a TaskManager.
 */
public class EventCommand extends Command {
    public static final String COMMAND_STRING = "event";
    public static final CommandType COMMAND_TYPE = CommandType.EVENT;

    private final String description;
    private final LocalDate date;

    /**
     * Creates an EventCommand that would add a Event with the supplied
     * description and date to a TaskManager when executed.
     *
     * @param description Description of the Event to be added.
     * @param date Date of the Event to be added.
     */
    private EventCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Constructs an EventCommand from a commandMap.
     *
     * @param commandMap CommandMap representing the instruction.
     * @return EventCommand object based on the commandMap.
     * @throws DukeException When the user inputs an illegal instruction for the
     * Event command.
     */
    public static EventCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        String description;
        LocalDate date;

        assert Parser.extractCommandString(commandMap).equals(COMMAND_STRING)
                : COMMAND_STRING + "CommandFlag does not match";

        // Validate description
        List<String> descriptionStrings = commandMap.get(COMMAND_STRING);
        if (descriptionStrings.isEmpty()) {
            throw new DukeException("Event description cannot be empty");
        }
        description = String.join(" ", descriptionStrings);

        // Validate date
        try {
            List<String> dateStrings = commandMap.get(Event.EVENT_DATE_STRING);
            String dateString = dateStrings.get(0);
            date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (NullPointerException e) {
            throw new DukeException("Please provide a date using the \"/at\" flag");
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("Please input date in the form YYYY-MM-DD");
        }

        return new EventCommand(description, date);
    }

    /**
     * Adds the specified Event Task to the supplied TaskManger.
     *
     * @param taskManager TaskManager object to add the Event Task to.
     * @return String response of the action that was performed.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return taskManager.addTask(new Event(description, date));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventCommand that = (EventCommand) o;
        return description.equals(that.description) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, date);
    }
}
