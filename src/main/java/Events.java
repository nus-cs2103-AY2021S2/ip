import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task.
 *
 * @author Arihant Jain
 */
public class Events extends Task {

    /**
     * The at.
     */
    private final String at;
    /**
     * The Date.
     */
    private LocalDate date;
    /**
     * The Start time.
     */
    private LocalTime startTime;
    /**
     * The End time.
     */
    private LocalTime endTime;

    /**
     * Instantiates a new Events.
     *
     * @param description the task description
     * @param at          the at
     */
    public Events(String description, String at) {
        super(description);
        this.at = at;
        setDateTime(this.at);
    }

    /**
     * Set date time.
     *
     * @param at Date and Time information
     */
    public void setDateTime(String at) {
        String atDate = at.substring(0, 10);
        String atTime = at.substring(11);
        String[] atDateArr = atDate.split("-");
        String[] atTimeArr = atTime.split("-");

        this.date = LocalDate.of(Integer.parseInt(atDateArr[0]),
                Integer.parseInt(atDateArr[1]),
                Integer.parseInt(atDateArr[2]));
        this.startTime = LocalTime.of(
                Integer.parseInt(atTimeArr[0].substring(0, 2)),
                Integer.parseInt(atTimeArr[0].substring(2)));
        this.endTime = LocalTime.of(
                Integer.parseInt(atTimeArr[1].substring(0, 2)),
                Integer.parseInt(atTimeArr[1].substring(2)));
    }

    /**
     * Get date string.
     *
     * @return the string
     */
    public String getDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return this.date.format(format);
    }

    /**
     * Get start time string.
     *
     * @return the string
     */
    public String getStartTime() {
        return this.startTime.toString();
    }

    /**
     * Get end time string.
     *
     * @return the string
     */
    public String getEndTime() {
        return this.endTime.toString();
    }

    /**
     * Gets at.
     *
     * @return the at
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Get date object.
     *
     * @return the local date.
     */
    public LocalDate getDateObj(){
        return this.date;
    }

    /**
     * Get start time object.
     *
     * @return the local time
     */
    public LocalTime getStartTimeObj(){
        return this.startTime;
    }

    /**
     * Get end time object.
     *
     * @return the local time
     */
    public LocalTime getEndTimeObj(){
        return this.endTime;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }
    /***
     * Get list variation Deadline Task String.
     * @return String Deadline Task String
     */

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (at: " + this.getDate()
                + " " + this.getStartTime()
                + "-" + this.getEndTime() + ")";
    }
}

