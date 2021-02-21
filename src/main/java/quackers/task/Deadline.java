package quackers.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final DateTimeFormatter DATETIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(Deadline.DATETIME_FORMATTER));
    }
}
