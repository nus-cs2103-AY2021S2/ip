package task;

import util.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class Event extends Task {
    public static final String COMMAND_STRING = "event";
    public static final String EVENT_DATE_STRING = "at";
    private final LocalDate eventDate;

    public Event(String desc, LocalDate eventDate) {
        super(desc);
        this.eventDate = eventDate;
    }

    public Event(String desc, LocalDate eventDate, boolean isDone) {
        super(desc, isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, d MMM yy");
        String dateString = eventDate.format(dateTimeFormatter);
        return "[E]" + super.toString() + " (at: " + dateString + ")";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public String getCommandString() {
        return COMMAND_STRING;
    }

    @Override
    public String toSaveString() {
        HashMap<String, List<String>> commandMap = new HashMap<>();

        // Add command flag
        commandMap.put(Parser.COMMAND_FLAG, new ArrayList<>());
        commandMap.get(Parser.COMMAND_FLAG).add(getCommandString());

        // Add description
        commandMap.put(getCommandString(), new ArrayList<>());
        commandMap.get(getCommandString()).add(super.getDescription());

        // Add end date
        commandMap.put(EVENT_DATE_STRING, new ArrayList<>());
        commandMap.get(EVENT_DATE_STRING).add(eventDate.format(ISO_LOCAL_DATE));

        return Parser.commandMapToString(commandMap);
    }

    public static Event fromSaveString(String saveString) {
        HashMap<String, List<String>> commandMap = Parser.parseCommandMap(saveString);

        List<String> descriptions = commandMap.get(COMMAND_STRING);
        String description = String.join(" ", descriptions);

        String eventDateString = commandMap.get(EVENT_DATE_STRING).get(0);
        LocalDate eventDate = LocalDate.parse(eventDateString);

        return new Event(description, eventDate);
    }
}
