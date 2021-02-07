package justin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * This class creates an Event
 * that is an extension of Task Class
 *
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */

public class Event extends Task {

    protected String dateTime;
    protected String date;
    protected String time;
    protected LocalDate date1;
    protected LocalTime time1;

    /**
     * This method creates an event class
     *
     * @param description of the given task
     * @param dateTime of the given task
     */

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
        String[] splits = dateTime.split("\\s+");
        this.date = splits[0];
        this.time = splits[1];
        this.date1 = LocalDate.parse(date);
        this.time1 = LocalTime.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                time1.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}