package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byTime;

    public Deadline(String desc) {
        super(desc);

        var parts = desc.split("/by");
        this.taskDescription = parts[0].trim();
        this.byTime = LocalDate.parse(parts[1].trim());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byTime.format(DateTimeFormatter.ISO_LOCAL_DATE) + ")";
    }
}
