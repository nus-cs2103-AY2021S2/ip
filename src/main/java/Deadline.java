import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate deadline;

    public Deadline(String str) {
        super(str);//super must be 1st line..
        String[] split = str.split("/");
        if(split.length < 2) {
            throw new IllegalArgumentException();
        }
        this.task = split[0].trim();
        this.deadline = LocalDate.parse(split[1].trim());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
