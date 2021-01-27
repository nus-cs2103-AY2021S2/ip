import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime at;
    protected final static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    public Event(String input) throws DukeException, DateTimeParseException {
        super(input.split(" /at ", 2)[0], "E");

        String[] split = input.split(" /at ", 2);
        if (split.length != 2) {
            throw new DukeException("Event command must follow the format: description /at time");
        }

        String[] datetime = split[1].split(" ");
        String date = datetime[0];
        String time = datetime.length > 1 // if time is not provided, default time is 23:59:59
                ? datetime[1].substring(0, 2) + ":" + datetime[1].substring(2, 4)
                : "23:59:59";

        this.at = LocalDateTime.parse(date + "T" + time);
    }

    public String toString() {
        return super.toString() + " (at: " + at.format(DATE_TIME_FORMAT) + ")";
    }

    public String toLog() {
        return super.toLog() + " | " + at;
    }
}
