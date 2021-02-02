import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Deadline extends Task {
    private LocalDate endDate;
    public static final String COMMAND_STRING = "deadline";

    public Deadline(String desc, LocalDate endDate) {
        super(desc);
        this.endDate = endDate;
    }

    public Deadline(String desc, LocalDate endDate, boolean isDone) {
        super(desc, isDone);
        this.endDate = endDate;
    }

    public static Deadline newInstance(HashMap<String, String> argMap)
            throws NoSuchElementException, DateTimeParseException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        LocalDate eventTime = null;
        boolean isDone = argMap.containsKey("done");

        if (argMap.containsKey("by")) {
            eventTime = LocalDate.parse(argMap.get("by"), ISO_LOCAL_DATE);
        }

        return new Deadline(desc, eventTime, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                (endDate != null
                        ? "(by: " + endDate.format(DateTimeFormatter.ofPattern("E, d MMM yy")) + ")"
                        : "");
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
