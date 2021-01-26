import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate date;
    protected String time;
    static int DATE_LENGTH = 10;

    Deadline(String task, String by) {
        super(task);
        this.date = LocalDate.parse(by.substring(0, DATE_LENGTH));
        this.time = by.substring(DATE_LENGTH);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " "
                + this.time + ")";
    }
}
