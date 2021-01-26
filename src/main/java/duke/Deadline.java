package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    Deadline(String description, boolean isCompleted, String deadline) {
        super(description, isCompleted);
        String[] dateAndTime = deadline.split(" ");
        String date = dateAndTime[0];
        this.date = LocalDate.parse(date);
        if (dateAndTime.length == 2) {
            String time = dateAndTime[1];
            this.time = LocalTime.parse(time);
        } else {
            this.time = null;
        }
    }

    @Override
    public LocalDate getTaskDate() {
        return this.date;
    }

    @Override
    public String getFormattedData() {
        String currentTime = time == null ? "" : " " + time;
        return  "D | " + super.getFormattedData() + " | " + date + currentTime;
    }

    @Override
    public String toString() {
        String exactTime = time == null ? "" : " " + time.format(DateTimeFormatter.ofPattern("hh:mma"));
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + exactTime + ")";
    }
}
