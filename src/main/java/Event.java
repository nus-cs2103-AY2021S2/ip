import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Event extends Task {
    private String eventTime;
    public static final char TYPE_SYMBOL = 'E';

    public Event(String desc, String eventTime) {
        super(desc);
        this.eventTime = eventTime;
    }

    public static Event newInstance(HashMap<String, String> argMap) throws NoSuchElementException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        String eventTime = argMap.getOrDefault("at", "N/A");
        return new Event(desc, eventTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + eventTime + ")";
    }

    @Override
    public String toSaveFormat() {
        return toSaveFormatPrefix() + saveDelimiter + eventTime;
    }

    @Override
    public char typeSymbol() {
        return TYPE_SYMBOL;
    }
}
