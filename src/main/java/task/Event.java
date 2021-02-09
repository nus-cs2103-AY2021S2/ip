package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class Event extends Task {
    public static final String COMMAND_STRING = "event";
    private final LocalDate eventTime;

    public Event(String desc, LocalDate eventTime) {
        super(desc);
        this.eventTime = eventTime;
    }

    public Event(String desc, LocalDate eventTime, boolean isDone) {
        super(desc, isDone);
        this.eventTime = eventTime;
    }

    public static Event newInstance(HashMap<String, String> argMap)
            throws NoSuchElementException, DateTimeParseException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        LocalDate eventTime = null;
        boolean isDone = argMap.containsKey("done");

        if (argMap.containsKey("at")) {
            eventTime = LocalDate.parse(argMap.get("at"), ISO_LOCAL_DATE);
        }

        return new Event(desc, eventTime, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                (eventTime != null
                        ? "(at: " + eventTime.format(DateTimeFormatter.ofPattern("E, d MMM yy")) + ")"
                        : "");
    }

    @Override
    protected HashMap<String, String> saveArgs() {
        HashMap<String, String> argMap = new HashMap<>();
        if (eventTime != null) {
            argMap.put("at", eventTime.toString());
        }
        return argMap;
    }

    @Override
    public String commandString() {
        return COMMAND_STRING;
    }
}
