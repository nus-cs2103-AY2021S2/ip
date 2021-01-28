package duke.tasks;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String afterBy;
    public Deadline (String info, String afterBy) {
        super(info, taskType.Deadline);
        this.afterBy = afterBy.trim();
    }

    public Deadline(String info, String afterBy, boolean isDone) {
        super(info, isDone);
        this.type = taskType.Deadline;
        this.afterBy = afterBy.trim();
    }

    /**
     * Returns the due date and time (if available) of the Deadline object.
     * @return date and time (if available)
     */

    public String getDateTime() {
        //Example of date based on format: 10 Aug 2021
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String[] dateList = this.afterBy.split(" ", 2);
        LocalDate deadlineDate = LocalDate.parse(dateList[0]);

        if (dateList.length >= 2) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
            LocalTime time = LocalTime.parse(dateList[1]);

            return deadlineDate.format(dateFormat) + " " + time.format(timeFormat);
        } else {
            return deadlineDate.toString();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}

