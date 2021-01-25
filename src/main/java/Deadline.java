import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate date;
    protected LocalTime time;

    Deadline(String description, String deadline) {
        super(description);
        String[] dateTime = deadline.split(" ");
        String deadlineDate = dateTime[0];
        this.date = LocalDate.parse(deadlineDate);
        if (dateTime.length == 2) {
            String deadlineTime = dateTime[1];
            this.time = LocalTime.parse(deadlineTime);
        } else {
            this.time = null;
        }
    }

    @Override
    public LocalDate getTaskDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String exactTime = time == null ? "" : " " + time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[D]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + exactTime + ")";
    }
}
