import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime cutOffTime;

    public Deadline(String name, LocalDateTime cutOff) {
        super(name);
        cutOffTime = cutOff;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + cutOffTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH : mm"))+ ")";
    }
}

