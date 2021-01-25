import java.time.LocalDate;

public class DeadlineTask extends Task {
//    protected String by;
    protected LocalDate by;

//    public DeadlineTask(String description, String by) {
//        super(description);
//        this.by = by;
//    }

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
