import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a Deadline task. Inherits from Task.
 */
public class Deadline extends Task {
    String deadLine;
    LocalDate localDate;
    LocalTime localTime;

    /**
     * Class constructor. Creates a not done Deadline object with name set to the specified taskName
     * and deadline set to the specified deadLine. Converts and stores the deadline as LocalDate and LocalTime.
     * @param taskName The specified name of the new Deadline object.
     * @param deadLine The specified deadline of the new Deadline object.
     */
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
            this.deadLine += " 0000";
        }
    }

    /**
     * A method to create a neatly formatted String that describes this Deadline.
     * @return Neatly formatted String representation of this Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                LocalDateTime.of(localDate, localTime).format(DateTimeFormatter.ofPattern("MMM d yyy h:m a")) + ")";
    }

    /**
     * Generates a formatted String for storage read and write purposes.
     * @return Formatted data String to be used by Storage.
     */
    @Override
    public String generateDataString() {
        return "deadline " + deadLine + (done ? " done " : " notDone ") + taskName;
    }
}
