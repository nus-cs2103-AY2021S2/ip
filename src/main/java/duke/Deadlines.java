package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDate deadlineTime;

    public Deadlines(String description, LocalDate deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + "(by: " + this.deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public String saveStatus() {
        return "D" + super.saveStatus();
    }
}
