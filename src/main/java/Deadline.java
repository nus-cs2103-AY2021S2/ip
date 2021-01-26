import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Deadline extends Task {
    protected boolean isDone;
    protected LocalDate time;
    protected final static String type = "[D]";

    public Deadline(String description, LocalDate time) {
        super(description);
        this.isDone = false;
        this.time = time;
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (by: " + getTime() + ")";
    }

}
