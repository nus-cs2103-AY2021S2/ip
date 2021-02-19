package core.task;

import java.time.LocalDate;

public abstract class TimedTask extends Task{
    protected LocalDate time;

    public TimedTask(String desc, String delimiter) {
        super(desc);
        assert(desc.contains(delimiter));
        var parts = desc.split(delimiter);
        this.taskDescription = parts[0].trim();
        this.time = LocalDate.parse(parts[1].trim());
    }

    protected String customFormat() {
        return "" + time.getDayOfMonth() + " : " + time.getMonth() + " : " + time.getYear();
    }
}
