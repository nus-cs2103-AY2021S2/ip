import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime time;

    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd Hmm"));
    }

    @Override
    public String toSaveFormat() {
        String status = super.isDone ? "1" : "0";
        return String.format("D|%s|%s\n", status, super.description, this.time);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }
}
