import java.util.NoSuchElementException;
import java.util.Optional;

public class Event extends Task {
    private String eventTime;
    public static final char TYPE_SYMBOL = 'E';

    public Event(String desc, String eventTime) {
        super(desc);
        this.eventTime = eventTime;
    }

    public static Event newInstance(String args) {
        String[] argArr = args.split("/");
        for (String s: argArr) {
            if (s.startsWith("at ")) {
                return new Event(argArr[0], s.substring(3));
            }
        }
        return new Event(argArr[0], "N/A");
    }

    public static Event newInstance(Optional<String> argsOpt) throws NoSuchElementException{
        String[] argArr;

        try {
            argArr = argsOpt.get().split("/");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: The description for event cannot be empty");
        }

        for (String s: argArr) {
            if (s.startsWith("at ")) {
                return new Event(argArr[0], s.substring(3));
            }
        }
        return new Event(argArr[0], "N/A");
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
