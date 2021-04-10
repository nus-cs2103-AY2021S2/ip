package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadlineDate;

    public Deadline(LocalDate deadlineDate, String deadlineDetail) {
        super(deadlineDetail);
        //YYYY-MM-dd
        this.deadlineDate = deadlineDate;
    }

    public LocalDate getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        return "[D] | " + super.toString() + " | by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM YYYY"));
    }
}
