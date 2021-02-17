package duke.tasks;

import duke.exceptions.DukeDateParseException;
import duke.parser.DateParser;


/**
 * A type of Task that will happen at some point in the future.
 */
public class Event extends Task {

    protected String at;
    protected String atToPrint;

    /**
     * Constructor.
     * @param description
     * @param at
     */

    public Event(String description, String at) throws DukeDateParseException {
        super(description, "E");
        this.at = at;
        this.localDate = DateParser.parseLocalDate(at);
        this.atToPrint = DateParser.replaceDate(at,localDate);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + atToPrint + ")";
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
