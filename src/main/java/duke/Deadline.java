package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task{
    protected LocalDate date;
    protected LocalTime time;


    Deadline(String description, boolean isCompleted, String deadline) {
        super(description, isCompleted);
        String[] dateTime = deadline.split(" ");
        String deadlineDate = dateTime[0];
        this.date = LocalDate.parse(deadlineDate);
        if (dateTime.length == 2) {
            String deadlineTime = dateTime[1];
            this.time = LocalTime.parse(deadlineTime);
        } else {
            this.time = null;
        }
    }

    @Override
    public LocalDate getTaskDate() {
        return this.date;
    }

    /**
     * Returns formatted string of the deadline task details to store in harddisk file.
     * @return Formatted string.
     */
    @Override
    public String getFormattedData() {
        String currentTime = time == null ? "" : " " + time;
        return  "D | " + super.getFormattedData() + " | " + date + currentTime;
    }

    /**
     * Returns description of the deadline task, status of the task as well as its due date and time.
     * @return String.
     */
    @Override
    public String toString() {
        String exactTime = time == null ? "" : " " + time.format(DateTimeFormatter.ofPattern("hh:mma"));
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + exactTime + ")";
    }
}
