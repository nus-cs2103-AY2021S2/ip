package ip.src.main.java;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event is a type of Task with attributes String content and a Date stored as a Date object.
 *
 */
public class Event extends Task {
    protected Date at;

    /**
     * Constructor for Event Class.
     *
     * @param content String content given by user.
     * @param atStr Event date given by user, parsed into a Date object with the format dd/MM/yyyy HH:mm.
     */
    public Event(String content , String atStr) {
        super(content);
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date at = sourceFormat.parse(atStr);
            this.at = at;
        } catch (ParseException e) {
            System.out.println("Date and Time is not in correct format DD/MM/YYYY HH:MM");
            e.printStackTrace();
        }

    }

    /**
     *Edits the date and time of the Event task by cloning the old Event task.
     *
     * @param newDate New date given by user.
     * @return A clone of the old Event task with the new date and time change.
     */
    public Event editDate (String newDate) {
        String content = this.content;
        boolean doneStatus = this.done;
        Event newEventTask = new Event (content , newDate);
        newEventTask.done = doneStatus;
        return newEventTask;
    }

    /**
     *Edits the content of the Event task by cloning the old Event task.
     *
     * @param newContent New content given by user.
     * @return A clone of the old Event task with the new content change.
     */
    public Event editContent (String newContent) {
        Event newTask = new Event(newContent , "0/0/0 00:00");
        newTask.at = this.at;
        newTask.done = this.done;
        return newTask;
    }

    /**
     * toString() of Event Class.
     *
     * @return toString() representation of a Event object with its done status, content and date.
     */
    @Override
    public String toString() {
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String strDate = sourceFormat.format(this.at);
        if (!this.done) {
            return "E | 0 | " + super.toString() + " | " + strDate;
        } else {
            return "E | 1 | " + super.toString() + " | " + strDate;
        }
    }
}
