import java.time.LocalDate;
import java.time.Month;

/**
 * Type of task that the user can input.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo objects.
     * @param description Describes what is to be done.
     */
    Todo(String description) {
        super(description);
    }

    /**
     * Getter that returns when the Todo is to be done by.
     * @return The date.
     */
    @Override
    public LocalDate getDate() {
        return LocalDate.of(2020, 1, 25);
    }

    /**
     * Getter which returns an identifier for the type of task it is.
     * @return T for Todo.
     */
    @Override
    public String getInitial() {
        return "T";
    }

    /**
     * String representation of a Todo object.
     * @return String with the description of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
