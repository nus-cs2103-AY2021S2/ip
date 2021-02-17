package duke.tasks;

import duke.exceptions.DukeParseException;
import duke.parser.DateParser;

import java.time.format.DateTimeFormatter;

/**
 * A type of Task that will happen at some point in the future.
 */
public class Event extends Task {

    private static final String EVENT_FORMAT_ERROR_MESSAGE =
            "Sorry Unable to Parse Date for Event. "
            + "Did you put in yyyy-mm-dd format?";

    protected String at;

    /**
     * Constructor.
     * @param description
     * @param at
     */

    public Event(String description, String at) throws DukeParseException {
        super(description, "E");

        String dateString = DateParser.extractDate(at);
        if (!dateString.equals("")) {
            this.localDate = DateParser.parseDate(dateString);
            this.at = at;
        } else {
            throw new DukeParseException(EVENT_FORMAT_ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        String dateString = DateParser.extractDate(at);
        String convertedDateString = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String modifiedAt = at.replaceAll(dateString, convertedDateString);
        return super.toString() + " (at: " + modifiedAt + ")";
    }

    /**
     * Gets the string representation used to save the task in the hard disk.
     *
     * @return string representation to be saved in the hard disk.
     */
    @Override
    public String getSavedStringFormat() {
        return super.getSavedStringFormat() + " | " + this.at;
    }
}
