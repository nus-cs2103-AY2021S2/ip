package Task;

import Utils.DateTime;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String serialise() {
        String type = "DEADLINE";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|').append(isDone).append('|').append(description).append('|').append(DateTime.serialiseDate(by));

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.getDate(by) + ")";
    }
}
