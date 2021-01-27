import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime; //in 24h format
    private final LocalTime endTime;

    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String generateText() {
        return String.format("E # %d # %s # %s %s-%s", this.isDone ? 1 : 0, this.description, this.date,
                this.startTime.format(
                        DateTimeFormatter.ofPattern("HHmm")),
                this.endTime.format(
                        DateTimeFormatter.ofPattern("HHmm")));
    }

    public String getTime() {
        return "(at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + this.startTime.format(DateTimeFormatter.ofPattern("hh:mma")) + "-"
                        + this.endTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + this.description + this.getTime();
        } else {
            return "[E][ ] " + this.description + this.getTime();
        }
    }
}
