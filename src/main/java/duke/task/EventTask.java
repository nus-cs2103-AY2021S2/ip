package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a event task.
 */
public class EventTask extends Task {

    protected String eventDate;

    /**
     * Creates a new instance of <code>EventTask</code>.
     * @param description Description of event task.
     */
    public EventTask(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Returns date of event.
     *
     * @return Date of event.
     */
    public LocalDate getEventDateDate() {
        String[] eventDateArr = this.eventDate.split(" ");
        String eventDateDateString = eventDateArr[0];
        LocalDate eventDateDate = LocalDate.parse(eventDateDateString);
        return eventDateDate;
    }

    /**
     * Returns time of event.
     *
     * @return Time of event.
     */
    public LocalTime getEventDateTime() {
        String[] eventDateArr = this.eventDate.split(" ");
        String eventDateTimeString = eventDateArr[1];
        LocalTime eventDateTime = LocalTime.parse(eventDateTimeString);
        return eventDateTime;
    }

    /**
     * Checks if user input has the <code>/at</code> keyword.
     *
     * @param description String input given by user after <code>event</code>.
     * @return True if user input has the <code>/at</code> keyword.
     */
    public static boolean hasAtKeyword(String description) {
        String[] descriptionArr = description.split(" /at ");
        return descriptionArr.length != 1;
    }

    /**
     * Checks if user input has the correct date & time format.
     *
     * @param description String input given by user after <code>event</code>.
     * @return True if user input has the correct date & time format.
     */
    public static boolean hasCorrectDateTimeFormat(String description) {
        String[] descriptionArr = description.split(" /at ");

        Pattern correctDateTimePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
        String inputDateTimeString = descriptionArr[1];
        Matcher matcher = correctDateTimePattern.matcher(inputDateTimeString);

        return matcher.find();
    }

    /**
     * Checks if user input has the correct format.
     *
     * @param description String input given by user after <code>event</code>.
     * @throws DukeException If user input does not have the correct format.
     */
    public static void checkFormat(String description) throws DukeException {
        if (!EventTask.hasAtKeyword(description)) {
            throw new DukeException("Your description is not given in the correct format!");
        } else if (!EventTask.hasCorrectDateTimeFormat(description)) {
            throw new DukeException("Your event date and time are given in the wrong format! "
                    + "Please make sure it is in the following format: YYYY-MM-DD HH:MM");
        }
    }

    /**
     * Returns String representation of event task.
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.description
                + " (at: " + this.getEventDateDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.getEventDateTime().format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }
}
