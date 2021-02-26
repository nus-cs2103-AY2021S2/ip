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
    protected String afterAt;
    protected String location;
    protected String date;
    protected String time;

    /**
     * Default constructor used when a new Event task is added
     *
     * @param info name of the task
     * @param afterAt details after "/at" in the input
     */
    public Event (String info, String afterAt) {
        super(info, taskType.EVENT);
        this.afterAt = afterAt.trim();
        String[] afterAtList = this.afterAt.trim().split(" ", 3);
        this.location = afterAtList[0];
        this.date = afterAtList[1];

        if (afterAtList.length == 3) {
            this.time = afterAtList[2];
        }

    }

    /**
     * Secondary constructor used when a new Event task is loaded
     * from a .txt file
     *
     * @param info name of the task
     * @param afterAt details after "/at" in the input
     * @param isDone boolean value indicating whether task is done
     */
    public Event(String info, String afterAt, boolean isDone) {
        super(info, taskType.EVENT, isDone);
        this.afterAt = afterAt.trim();
        String[] afterAtList = this.afterAt.split(" ", 3);
        this.location = afterAtList[0];
        this.date = afterAtList[1];

        if (afterAtList.length == 3) {
            this.time = afterAtList[2];
        }
    }

    /**
     * Returns the due date and time (if available) of the Event object.
     *
     * @return date and time (if available)
     */
    public String getDateTime() {
        //Example of date based on format: 10 Aug 2021
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate eventDate;
            eventDate = LocalDate.parse(date);

        if (time != null) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");
            LocalTime eventTime = LocalTime.parse(time);
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

    /**
     * Returns the location and datetime of the Event
     * before being parsed
     *
     * @return String which is the unparsed location and datetime of the Event object
     */
    public String getAfterAt() {
        return this.afterAt;
    }

    @Override
    public String toSave() {
            return this.getType().toString() + " "
                    + this.getInfo().trim()
                    + "/at"
                    + this.getAfterAt().trim();
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: "
                + getLocation() + " " + getDateTime() + ")";
    }
}
