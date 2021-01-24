package fakebot.task;

import java.time.LocalDate;
import java.time.LocalTime;

//Deadline class with Date
public class Deadlines extends Task {
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public Deadlines(String name, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(name);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;

    }

    public String getDeadlineDate() {
        return saveDateFormat.format(deadlineDate);
    }

    public String getDeadlineTime() {
        return saveTimeFormat.format(deadlineTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + printDateFormat.format(deadlineDate) + " " + printTimeFormat.format(deadlineTime) + ")";
    }
}

