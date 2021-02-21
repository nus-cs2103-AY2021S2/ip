package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DueDate is an interface for inheritance by tasks that has a due date.
 */
public interface DueDate {
    /**
     * Returns a String representation of the task.
     *
     * @return A String representation of the task.
     */
    String getDueDate();

}
