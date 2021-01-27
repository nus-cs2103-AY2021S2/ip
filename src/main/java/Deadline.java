import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
   * Deadline inherits Task
   * @param command the main action of the command
   * @param description the description of the task
   * @param by the due date specified by user
   */
class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }
    @Override
    LocalDate getTime(){
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}