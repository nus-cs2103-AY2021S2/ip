package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of Task that will happen at some point in the future.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate dateOfEvent;
    public Event(String description,String at) {
        super(description, "E");

        String dateString = Parser.extractDate(at);
        if (!dateString.equals("")) {
            this.dateOfEvent = Parser.parseDate(dateString);
            this.at = at;
        } else {
            throw new IllegalArgumentException("Sorry Unable to Parse Date for Event. Did you put in yyyy-mm-dd format?");
        }
    }

    @Override
    public String toString() {
        String dateString = Parser.extractDate(at);
        String convertedDateString = dateOfEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy"));  // convert the format
        String modified_at = at.replaceAll(dateString, convertedDateString);
        return super.toString() + " (at: " + modified_at + ")";
    }

    @Override
    public String getSavedStringFormat() {
        return super.getSavedStringFormat() + " | " + this.at;
    }
}
