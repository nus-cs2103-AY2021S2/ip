package command;

import task.Event;
import task.TaskManager;
import util.DukeException;
import util.Input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EventCommand implements Command {
    public static final String COMMAND = "event";
    private static final CommandType COMMAND_TYPE = CommandType.DEADLINE;

    private final String description;
    private final LocalDate date;
    private String message;

    private EventCommand(String description, LocalDate date){
        this.description = description;
        this.date = date;
    }

    public static EventCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        String description;
        LocalDate date;

        // Validate description
        List<String> descriptionStrings = commandMap.get(COMMAND);
        if (descriptionStrings.isEmpty()) {
            throw new DukeException("Event description cannot be empty");
        }
        description = descriptionStrings.stream()
                .collect(Collectors.joining(" "));

        // Validate date
        try {
            List<String> dateStrings = commandMap.get(Event.EVENT_DATE_STRING);
            String dateString = dateStrings.get(0);
            date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (NullPointerException e) {
            throw new DukeException("Please provide a date");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input date in the form YYYY-MM-DD");
        }

        return new EventCommand(description, date);
    }

    @Override
    public void execute(TaskManager taskManager) {
        message = taskManager.addTask(new Event(description, date));
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public CommandType commandType() {
        return COMMAND_TYPE;
    }
}
