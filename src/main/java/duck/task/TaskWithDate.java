package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskWithDate extends Task {
    protected LocalDate date;

    /**
     * initialize Deadline object
     *
     * @param description the description of task
     * @param date        the date of task (YYYY-MM-DD)
     */
    public TaskWithDate(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
        ;
    }

    /**
     * get task date
     *
     * @return date string
     */
    public String getDate() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.date.format(fmt);
    }
}
