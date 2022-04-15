import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
   * Deadline inherits Task
   * @param command the main action of the command
   * @param description the description of the task
   * @param by the due date specified by user
   */
class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }
    @Override
    LocalDate getTime(){
        assert (this.date != null);
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}