package command;

import task.Event;
import task.TaskManager;
import util.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

public class EventCommand extends Command {
    public static final String COMMAND_STRING = "event";
    public static final CommandType COMMAND_TYPE = CommandType.EVENT;

    private final String description;
    private final LocalDate date;

    private EventCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public static EventCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        String description;
        LocalDate date;

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
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input date in the form YYYY-MM-DD");
        }

        return new EventCommand(description, date);
    }

    @Override
    public String execute(TaskManager taskManager) {
        return taskManager.addTask(new Event(description, date));
    }
}
