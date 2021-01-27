import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String deadLine;
    LocalDate localDate;
    LocalTime localTime;

    public Deadline(String taskName, String deadLine) {
        super(taskName);
        this.deadLine = deadLine;

        String[] deadLineSplit = deadLine.split(" ");

        localDate = LocalDate.parse(deadLineSplit[0]);

        if (deadLineSplit.length > 1) {
            int time = Integer.parseInt(deadLineSplit[1]);
            localTime = LocalTime.of(time/100, time % 100);
        } else {
            localTime = LocalTime.MIDNIGHT;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                LocalDateTime.of(localDate, localTime).format(DateTimeFormatter.ofPattern("MMM d yyy h:m a")) + ")";
    }
}
