import java.time.LocalDate;

/**
 * Represents a task in the form of a ToDo.
 */
public class ToDo extends Task {

    /**
     * Constructor takes in one parameter, <code>description</code>.
     * @param description a description of the to do
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Generates the text of this task in the proper format to be stored
     * into the text file at the end of execution.
     * @return A <code>String</code> text in the proper format to be stored
     * into the text file
     */
    @Override
    public String generateText() {
        return String.format("T # %d # %s",
                                this.isDone ? 1 : 0,
                                        this.description);
    }

    @Override
    public ToDo reschedule(String fullCommand) {
        return this;
    }

    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }
    }
}
