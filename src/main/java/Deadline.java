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
     * Overloaded constructor method
     * @param description task description
     * @param deadline task deadline
     * @param isDone task done status
     */
    public Deadline(String description, LocalDate deadline, boolean isDone){
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Getter method for task deadline.
     * @return string of task deadline
     */
    public LocalDate getDeadline(){
        return this.deadline;
    }

    public LocalDate changeDeadline(LocalDate deadline){
        this.deadline = deadline;
        return this.deadline;
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}