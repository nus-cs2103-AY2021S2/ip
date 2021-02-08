package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * initialize Deadline object
     *
     * @param description the description of task
     * @param by          the deadline of task (YYYY-MM-DD)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        ;
    }

    /**
     * get task information
     *
     * @return a string show information of task
     */
    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfo() + " (by: "
                + by.getDayOfWeek() + "," + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * get period from now to deadline
     *
     * @return the number of period
     */
    @Override
    public String getPeriodDays() {
        LocalDate now = LocalDate.parse(LocalDate.now().toString());
        return "There are " + String.valueOf(now.until(by, ChronoUnit.DAYS)) + "day(s) before the deadline";
    }

    /**
     * get the task information showing in the file
     *
     * @return
     */
    @Override
    public String getTaskInfoOfFile() {
        return "D | " + (super.isDone ? "1" : "0") + " | " + super.getDescription() + " | " + this.by;
    }

}
