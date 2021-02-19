package duke.tasks;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A children of the Task class with additional details
 * stored like date and time of deadline object and functions
 * to return these details.
 */
public class Deadline extends Task {
    protected String afterBy;
    protected String date;
    protected String time;

    /**
     * Default constructor used when a new Deadline task is added
     *
     * @param info, name of the task
     * @param afterBy details after "/by" in the input
     */
    public Deadline (String info, String afterBy) {
        super(info, taskType.DEADLINE);
        this.afterBy = afterBy.trim();
        String[] afterByList = this.afterBy.split(" ", 2);
        this.date = afterByList[0];

        if (afterByList.length == 3) {
            this.time = afterByList[1];
        }
    }

    /**
     * Secondary constructor used when the task is loaded from a
     * .txt file
     *
     * @param info, name of the task
     * @param afterBy, details after "/by" in the input
     * @param isDone, boolean value to indicate whether task is done
     */
    public Deadline(String info, String afterBy, boolean isDone) {
        super(info, taskType.DEADLINE, isDone);
        this.afterBy = afterBy.trim();
        String[] afterByList = this.afterBy.split(" ", 2);
        this.date = afterByList[0];

        if (afterByList.length == 3) {
            this.time = afterByList[1];
        }
    }

    /**
     * Returns string containing details of Deadline object after /by
     *
     * @return String with details of after /by
     */
    public String getAfterBy() {
        return this.afterBy;
    }

    /**
     * Returns the due date and time (if available) of the Deadline object.
     *
     * @return date and time (if available)
     */
    public String getDateTime() {
        //Example of date based on format: 10 Aug 2021
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String[] dateList = this.afterBy.split(" ", 2);
        LocalDate deadlineDate;

        deadlineDate = LocalDate.parse(dateList[0]);

        if (dateList.length >= 2) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");
            LocalTime time = LocalTime.parse(dateList[1]);

            return deadlineDate.format(dateFormat) + " " + time.format(timeFormat);
        } else {
            return deadlineDate.toString();
        }
    }

    @Override
    public String toSave() {
        return this.getType().toString() + " "
                + this.getInfo().trim()
                + "/by"
                + this.getAfterBy().trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}

