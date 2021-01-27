package stub;

import task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadlineStub extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public DeadlineStub(String description, String duration) {
        this.type = "D";
        this.description = description;
        if (description.length() != 0 && duration.length() != 0) {
            String[] input = duration.split("\\s+");
            this.date = LocalDate.parse(input[0]);
            if (input.length > 1) {
                this.time = LocalTime.parse(input[1]);
            }
        }
    }


    @Override
    public String toString() {
        if (this.time == null) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: "
                    + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
        } else {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: "
                    + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    + " " + this.time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
        }
    }
}
