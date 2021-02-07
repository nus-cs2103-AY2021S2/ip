package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A children of the Task class with additional details
 * stored like location, date and time of deadline object
 * and functions to return these details.
 */
public class Event extends Task{
    protected String location;
    protected String dateTime;


    /**
     * Default constructor used when a new Event task is added
     *
     * @param info, name of the task
     * @param afterAt details after "/at" in the input
     */
    public Event (String info, String afterAt) {
        super(info, taskType.Event);
        String[] afterAtList = afterAt.trim().split(" ", 2);
        this.location = afterAtList[0];
        this.dateTime = afterAtList[1];

    }

    /**
     * Secondary constructor used when a new Event task is loaded
     * from a .txt file
     *
     * @param info, name of the task
     * @param afterAt, details after "/at" in the input
     * @param isDone, boolean value indicating whether task is done
     */
    public Event(String info, String afterAt, boolean isDone) {
        super(info, taskType.Event, isDone);
        String[] afterAtList = afterAt.split(" ", 2);
        this.location = afterAtList[0];
        this.dateTime = afterAtList[1];
    }

    /**
     * Returns the due date and time (if available) of the Deadline object.
     * @return date and time (if available)
     */
    public String getDateTime() {
        //Example of date based on format: 10 Aug 2021
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String[] dateList = this.dateTime.split(" ", 2);
        LocalDate eventDate = LocalDate.parse(dateList[0]);

        if (dateList.length == 2) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
            LocalTime eventTime = LocalTime.parse(dateList[1]);

            return eventDate.format(dateFormat) + " " + eventTime.format(timeFormat);
        } else {
            return eventDate.toString();
        }
    }

    /**
     * Returns the location of the event
     *
     * @return String which is location of the Event object
     */
    public String getLocation() {
        return this.location;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: "
                + getLocation() + " " + getDateTime() + ")";
    }
}
