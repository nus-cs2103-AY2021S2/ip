import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Deadline extends Task {
    private LocalDateTime endDate;
    public static final String COMMAND_STRING = "deadline";

    public Deadline(String desc, LocalDateTime endDate) {
        super(desc);
        this.endDate = endDate;
    }

    public Deadline(String desc, LocalDateTime endDate, boolean isDone) {
        super(desc, isDone);
        this.endDate = endDate;
    }

    public static Deadline newInstance(HashMap<String, String> argMap) throws NoSuchElementException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        LocalDateTime eventTime = null;
        boolean isDone = argMap.containsKey("done");

        if (argMap.containsKey("at")) {
            eventTime = LocalDateTime.parse(argMap.get("at"));
        }

        return new Deadline(desc, eventTime, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + endDate + ")";
    }

    @Override
    protected HashMap<String, String> saveArgs() {
        HashMap<String, String> argMap = new HashMap<>();
        if (endDate != null) {
            argMap.put("by", endDate.toString());
        }
        return argMap;
    }

    @Override
    public String commandString() {
        return COMMAND_STRING;
    }
}
