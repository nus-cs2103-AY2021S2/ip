import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate dateBy; //deadline of task
    private final LocalTime timeBy;

    public Deadline(String description, LocalDate dateBy, LocalTime timeBy) {
        super(description);
        this.dateBy = dateBy;
        this.timeBy = timeBy;
    }

    @Override
    public LocalDate getDate() {
        return this.dateBy;
    }

    @Override
    public String generateText() {
        return String.format("D # %d # %s # %s %s",
                this.isDone ? 1 : 0,
                        this.description, this.dateBy,
                                this.timeBy.format(
                                        DateTimeFormatter.ofPattern("HHmm")));
    }

    public String getDeadline() { //get deadline in format of String eg. (by: Sunday)
        return "(by: " + dateBy.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                        + timeBy.format(
                                DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][X] " + this.description + this.getDeadline();
        } else {
            return "[D][ ] " + this.description + this.getDeadline();
        }
    }
}
