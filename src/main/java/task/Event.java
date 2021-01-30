package task;

import static ui.Ui.printBox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import exception.DukeException;
/**
 * Class <code>Event</code> extends <code>Task</code>. It contains date
 * and time which describes the task.
 */
public class Event extends Task {
    protected LocalDate date = null;
    protected LocalTime time = null;

    /**
     * Constructs a Event task with description specified by <code>description</code> ,and date and time
     * specified by <code>duration</code>.
     *
     * @param description task description.
     * @param duration    task date and time.
     * @throws DukeException DukeException if task date or description is empty.
     */
    public Event(String description, String duration) throws DukeException {
        this.type = "E";
        if (description.length() != 0 && duration.length() != 0) {
            this.description = description;
            String[] input = duration.split("\\s+");
            try {
                this.date = LocalDate.parse(input[0]);
                this.time = LocalTime.parse(input[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                if (this.time == null) {
                    printBox("WARNING! No Time Detected!");
                } else if (this.date == null) {
                    throw new DukeException("☹ OOPS!!! Date of Event cannot be empty, please check!");
                } else {
                    throw new DukeException("☹ OOPS!!! Date & Time of Event cannot be empty, please check!");
                }
            }
        } else {
            if (description.length() == 0) {
                throw new DukeException("☹ OOPS!!! Description of Event cannot be empty, please check!");
            } else {
                throw new DukeException("☹ OOPS!!! Date of Event cannot be empty, please check!");
            }
        }
    }

    /**
     * Contructs a Event task with status as specified by <code>status</code> and the description,date and time
     * specified by <code>input</code>.
     *
     * @param status task status.
     * @param input  task description,date and time.
     * @throws DukeException DukeException if task date or description is empty.
     */

    public Event(String status, String[] input) throws DukeException {
        this.type = "E";
        this.isDone = status.equals("complete");
        try {
            this.description = input[0];
            this.date = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.time = LocalTime.parse(input[2], DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (ArrayIndexOutOfBoundsException ex) {
            if (this.time == null) {
                printBox("WARNING!!! \n"
                        + "     Event with no Timing Specified Detected!");
            } else if (this.date == null) {
                throw new DukeException("☹ OOPS!!! Date of Event cannot be empty, please check!");
            } else {
                throw new DukeException("☹ OOPS!!! Description of Event cannot be empty, please check!");
            }
        }
    }

    /**
     * Returns a string of the task's date with the format "dd MM yyyy".
     *
     * @return formatted date.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Return a string of the task's time with the format "hh:mm a".
     *
     * @return formatted time.
     */
    public String getTime() {
        if (this.time != null) {
            return this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else {
            return null;
        }
    }


    @Override
    public String toString() {
        if (this.time == null) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(at: "
                    + this.getDate() + ")";
        } else {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(at: "
                    + this.getDate() + " "
                    + this.getTime() + ")";
        }
    }

}
