import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String type = "E";

    public Event(String description, String at) {
        this(description, at, false);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, at, isDone);
    }

    /**
     * Return the due time of a Deadline task.
     *
     * @return Due time in String.
     */
    private String getTime() {
        // convert dateTime from "yyyy-m-d hh:mm" to "MMM d yyyy hh:mm a"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        String time = this.dateTime.format(format).replace("T", " ");
        return time;
    }

    @Override
    public String toString() {
        return type + separator + super.toString() + separator + getTime();
    }
}
