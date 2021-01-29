import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    LocalDate deadLine;

    public Deadlines(String eventName, String deadLine) {
        super(false, eventName);
        this.deadLine = LocalDate.parse(deadLine);
    }

    @Override
    public String toString() {

        return "[D] " + super.toString() + "(by: " + deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
