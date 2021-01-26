import java.time.LocalDate;

public class Deadline extends Task{
    protected LocalDate by;
    Deadline(String task, String by) {
        super(task);
        this.by = LocalDate.parse(by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + this.by + ")";
    }
}
