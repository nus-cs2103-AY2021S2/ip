package duck.task;

import duck.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        ;
    }

    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfo() + " (by: "
                + by.getDayOfWeek() + "," + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getPeriodDays() {
        LocalDate now = LocalDate.parse(LocalDate.now().toString());
        return "There are " + String.valueOf(now.until(by, ChronoUnit.DAYS)) + "day(s) before the deadline";
    }


    @Override
    public String getTaskInfoOfFile(){
        return "D | "+(super.isDone?"1":"0")+" | "+super.getDescription() +" | "+this.by;
    }

}
