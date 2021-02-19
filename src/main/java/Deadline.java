import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM" +
            "/yyyy HHmm");

    private LocalDateTime time;

    Deadline(String description) {
        super(description);
    }

    Deadline(String description, String eventDate) {
        super(description, eventDate);
        formatDate();

    }

    Deadline(String[] description) {
        super(description[0].substring(9), description[1]);
    }

    Deadline(String description, String eventDate, boolean isDone) {
        super(description, eventDate, isDone);
        formatDate();

    }

    /**
     * Method that formats the date into a standard format.
     */
    public void formatDate() {
        try {
            time = LocalDateTime.parse(this.eventDate, formatter);
        } catch (Exception e) {
            time = null;
        }
    }

    /**
     * Method that returns a standard formatted date.
     * @return A formatted date time string.
     */
    public String getDate() {
        if (time == null) {
            return this.eventDate;
        } else {
            return time.format(formatter);
        }
    }


    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns formatted string of deadline.
     * @return Formatted String representing deadline.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s(by:%s)", this.getTaskType(),
                this.getStatusIcon(), this.getDescription(), this.getDate());
    }


}
