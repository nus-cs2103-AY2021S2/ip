package task;

import exception.DukeException;

import static ui.Ui.printBox;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class <code>Deadline</code> extends <code>Task</code>. It contains date
 * and time which describes the task.
 */

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

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

    /**
     * Contructs a Deadline task with description as specified in <code>description</code>, and the time and
     * date specified by <code>duration</code> argument.
     *
     * @param str task description.
     * @throws DukeException DukeException if task date or description is empty.
     */
    public Deadline(String... str) throws DukeException {
        this.type = "D";
        if (str.length >= 1) {
            this.description = str[0];

            String[] input = str[1].split("\\s+");
            try {
                this.date = LocalDate.parse(input[0]);
                this.time = LocalTime.parse(input[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                if (this.time == null) {
                    printBox("WARNING! No Time Detected!");
                } else if (this.date == null) {
                    throw new DukeException("☹ OOPS!!! Date of Deadline cannot be empty, please check!");
                } else {
                    throw new DukeException("☹ OOPS!!! Date & Time of Deadline cannot be empty, please check!");
                }
            }
        } else {
            if (description.length() == 0) {
                throw new DukeException("☹ OOPS!!! Description of Deadline cannot be empty, please check!");
            } else {
                throw new DukeException("☹ OOPS!!! Date of Deadline cannot be empty, please check!");
            }
        }
    }

    /**
     * Contructs a Deadline task with status as specified by <code>status</code> and the description,date and time
     * specified by <code>input</code>.
     *
     * @param status task status.
     * @param input  task description,date and time.
     * @throws DukeException DukeException if task date or description is empty.
     */
    public Deadline(String status, String[] input) throws DukeException {
        this.type = "D";
        this.isDone = status.equals("complete");
        try {
            this.description = input[0];
            this.date = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.time = LocalTime.parse(input[2], DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (ArrayIndexOutOfBoundsException ex) {
            if (this.time == null) {
                printBox("WARNING!!! \n"
                        + "     Deadline with no Timing Specified Detected!");
            } else if (this.date == null) {
                throw new DukeException("☹ OOPS!!! Date of Deadline cannot be empty, please check!");
            } else {
                throw new DukeException("☹ OOPS!!! Description of Deadline cannot be empty, please check!");
            }
        }
    }

    @Override
    public String toString() {
        if (this.time == null) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                    + this.getDate() + ")";
        } else {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                    + this.getDate() + " " + this.getTime() + ")";
        }
    }
}
