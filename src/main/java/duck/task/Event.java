package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate at;

    /**
     * initialize Event object
     *
     * @param description the description of task
     * @param at the starting time of task (YYYY-MM-DD)
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * get task information
     *
     * @return a string show information of task
     */
    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfo() + " (at: "
                + at.getDayOfWeek() + "," + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * get period from now to starting time
     *
     * @return the number of period
     */
    @Override
    public String getPeriodDays() {
        LocalDate now = LocalDate.parse(LocalDate.now().toString());
        return "There are " + String.valueOf(now.until(at, ChronoUnit.DAYS)) + "day(s) before the event starting";
    }

    /**
     * get the task information showing in the file
     *
     * @return
     */
    @Override
    public String getTaskInfoOfFile() {
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.getDescription() + " | " + this.at;
    }
}
