import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected final static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    public Deadline(String input) throws DukeException {
        super(input.split(" /by ", 2)[0], "D");

        String[] split = input.split(" /by ", 2);
        if (split.length != 2) {
            throw new DukeException("Deadline command must follow the format: description /by time");
        }

        String[] datetime = split[1].split(" ");
        String date = datetime[0];
        String time = datetime.length > 1 // if time is not provided, default time is 23:59:59
                ? datetime[1].substring(0, 2) + ":" + datetime[1].substring(2, 4)
                : "23:59:59";

        this.by = LocalDateTime.parse(date + "T" + time);
    }

    public String toString() {
        return super.toString() + " (by: " + by.format(DATE_TIME_FORMAT) + ")";
    }

    public String toLog() {
        return super.toLog() + " | " + by;
    }
}
