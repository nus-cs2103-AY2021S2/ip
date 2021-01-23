package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    /** The deadlin of the deadline task */
    private LocalDate deadline;

    public DeadlineTask(String description, String deadline) {
        super(description, "[D]");
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns the deadline of the task formatted to be dd-MM-YYYY
     * with closing paranthesis
     *
     * @return deadline.
     */
    public String getDeadline() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return "(by: " + this.deadline.format(format) + ")";
    }

    /**
     * Returns the deadline of the deadline task formatted to be dd-MM-YYYY.
     *
     * @return deadline.
     */
    public String getUnformattedDeadline() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return this.deadline.format(format);
    }

    /**
     * Returns the deadline as a LocalDate Object
     *
     * @return deadline.
     */
    public LocalDate getDeadlineAsLocalDate() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "       " + this.type + super.toString().trim() + " " + this.getDeadline();
    }
}
