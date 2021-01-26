import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructor method.
     * @param description String that describes the task.
     * @param deadline String that describes the deadline.
     */
    public Deadline(String description, LocalDate deadline){
        super(description);
        this.deadline = deadline;
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ")";
    }
}
