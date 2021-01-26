import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of Task that will happen at some point in the future.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate dateOfEvent;
    public Event(String description,String at) {
        super(description);
        String dateString = Parser.extractDate(at);
        if (!dateString.equals("")) {
            this.dateOfEvent = Parser.parseDate(dateString);
            String convertedDateString = dateOfEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy"));  // convert the format
            this.at = at.replaceAll(dateString, convertedDateString);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
