import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * Simulates an event that occurs at a specific date and time.
 */
public class Event extends Task {

    /** Date of the event */
    private LocalDate date;

    /** Time of the event */
    private LocalTime time;

    /**
     * Creates a new Event.
     *
     * @param details Details of the event.
     * @param date Date of the event.
     * @param time Time of the event.
     */
    public Event(String details, LocalDate date, LocalTime time) {
        super(details);
        this.date = date;
        this.time = time;
    }

    /**
     * Constructor to complete an Event that is completed.
     *
     * @param details Details of the event.
     * @param date Date of the event.
     * @param time Time of the event.
     * @param indicator Denotes that event is completed regardless of boolean value passed.
     */
    public Event(String details, LocalDate date, LocalTime time, boolean indicator) {
        super(details, indicator);
        this.date = date;
        this.time = time;
    }

    /**
     * Completes the event.
     *
     * @return New completed Event with the same details.
     */
    public Event completeTask() {
        return new Event(this.getTask_details(), date, time,true);
    }

    /**
     * Returns a String describing the Event as well as its completion status.
     * @return
     */
    public String taskStatus() {
        if (this.isDone()) {
            return "E 1 "
                    + this.getTask_details()
                    + " (on: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                    + time.format(DateTimeFormatter.ofPattern("HHmm")) + " )";
        } else {
            return "E 0 "
                    + this.getTask_details()
                    + " (on: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                    + time.format(DateTimeFormatter.ofPattern("HHmm")) + " )";
        }
    }
}
