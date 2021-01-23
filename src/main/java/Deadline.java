import java.util.List;

/**
 * Represents an deadline task.
 * Has a due date.
 */
public class Deadline extends Task {

    private String dueDate;
    private static final String TYPE = "D";

    /**
     * Factory method for creating deadline task.
     * @param input Description of the task and its due date. Due date should be indicated after "/by".
     * @return A deadline task
     * @throws DukeException if due date is missing
     */
    public static Deadline createDeadline(String input) throws DukeException {
        String[] details = input.split(" /by ");

        if (!input.contains("/by") || details.length == 1) {
            throw new DukeException("Due date is missing!");
        }

        return new Deadline(details[0], details[1]);
    }

    private Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    private Deadline(boolean isDone, String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", TYPE, super.toString(), dueDate);
    }
    
    @Override
    public List<String> exportData() {
        return List.of(TYPE,
                isDone ? "1" : "0",
                description,
                dueDate);
    }

    public static Deadline importData(String[] args) {
        boolean isDone = args[1] == "1";
        return new Deadline(isDone, args[2], args[3]);
    }
}
