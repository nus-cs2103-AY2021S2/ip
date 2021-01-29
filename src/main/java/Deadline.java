import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, true);
        this.by = by;
    }

    public static Deadline create(String taskInfo) throws DukeWrongFormatException,
            DukeMissingDescriptionException {
        String[] parsedInfo = taskInfo.split(" /by ", 2);
        if (parsedInfo.length != 2) {
            throw new DukeWrongFormatException("deadline");
        } else if (parsedInfo[0].equals(" ") || parsedInfo[1].equals(" ")) {
            throw new DukeMissingDescriptionException("deadline");
        } else {
            try {
                LocalDateTime ldt = Parser.parseInputDate(parsedInfo[1]);
                return new Deadline(parsedInfo[0], ldt);
            } catch (DateTimeParseException e) {
                throw new DukeWrongFormatException("deadline");
            }
        }
    }

    @Override
    public Deadline finishTask() {
        return new Deadline(description, by, true);
    }

    @Override
    public String saveTask() {
        return String.format("D | %s | %s | %s\n", super.getStatusIcon(),
                description, super.timeFormat(by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + super.timeFormat(by) + ")";
    }
}