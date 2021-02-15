import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    private String type = "D";

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, by, isDone);
    }

    /**
     * Return the due time of a Deadline task.
     *
     * @return Due time in String.
     */
    private String getTime() {
        // convert dateTime from "yyyy-m-d hh:mm" to "MMM d yyyy hh:mm a"
        String time = this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")).replace("T", " ");
        return time;
    }

    @Override
    public String toString() {
        return type + separator + super.toString() + separator + this.getTime();
    }
}
