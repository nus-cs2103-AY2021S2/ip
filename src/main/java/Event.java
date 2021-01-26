import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Event extends Task {
    private LocalDateTime eventTime;
    public static final String COMMAND_STRING = "event";

    public Event(String desc, LocalDateTime eventTime) {
        super(desc);
        this.eventTime = eventTime;
    }

    public Event(String desc, LocalDateTime eventTime, boolean isDone) {
        super(desc, isDone);
        this.eventTime = eventTime;
    }

    public static Event newInstance(HashMap<String, String> argMap) throws NoSuchElementException, DateTimeParseException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        LocalDateTime eventTime = null;
        boolean isDone = argMap.containsKey("done");

        if (argMap.containsKey("at")) {
            eventTime = LocalDateTime.parse(argMap.get("at"));
        }

        return new Event(desc, eventTime, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + eventTime + ")";
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
