import java.time.LocalDate;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, Date by, String type) {
        super(description,type);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toFormattedString() + ")";
    }

}
