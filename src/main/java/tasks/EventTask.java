package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public EventTask(String description, LocalDate date) {
        super(description, 'E');
        this.date = date;
        this.startTime = null;
        this.endTime = null;
    }

    public EventTask(String description, LocalDate date, LocalTime startTime) {
        super(description, 'E');
        this.date = date;
        this.startTime = startTime;
        this.endTime = null;
    }

    public EventTask(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description, 'E');
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDateSaveString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getStartTimeString() {
        return this.startTime == null
                ? ""
                : " | " + this.startTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    public String getEndTimeString() {
        return this.endTime == null
                ? ""
                : " | " + this.endTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + " | " + getDateSaveString()
                + getStartTimeString() + getEndTimeString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + (this.startTime == null
                ? ""
                : " " + this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a")))
                + (this.endTime == null
                ? ""
                : "-" + this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a")))
                + ")";
    }
}
