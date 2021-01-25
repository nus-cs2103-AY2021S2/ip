package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfo() + " (at: "
                + at.getDayOfWeek() + "," + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getPeriodDays() {
        LocalDate now = LocalDate.parse(LocalDate.now().toString());
        return "There are " + String.valueOf(now.until(at, ChronoUnit.DAYS)) + "day(s) before the event starting";
    }

    @Override
    public String getTaskInfoOfFile() {
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.getDescription() + " | " + this.at;
    }
}
