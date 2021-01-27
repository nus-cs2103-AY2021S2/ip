import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, int status, LocalDateTime by) {
        super(description,status);
        this.by = by;
    }

    @Override
    public String toTxt(){
        return "D " + super.toTxt() + " | " + by.format(DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")) + ")";
    }
}
