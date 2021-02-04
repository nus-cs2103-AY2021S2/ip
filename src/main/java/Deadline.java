import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String desc, boolean isDone, String by) {
        super(desc, isDone);
        this.by = LocalDate.parse(by);
    }

    /**
     * Factory method to create Deadline task based on input
     *
     * @param input User input
     * @return Deadline object
     * @throws DukeException If parsing input fails
     */
    public static Deadline parse(String input) throws DukeException {
        String[] arr = input.split("deadline");
        if (arr.length < 2) {
            throw new DukeException("Deadline description cannot be empty");
        }

        String body = arr[1].strip();
        if (!body.trim().isEmpty()) {
            String[] parts = body.split("/by");
            String desc = parts[0].strip();
            String by = parts[1].strip();
            return new Deadline(desc, by);
        } else {
            throw new DukeException("Deadline description cannot be empty");
        }
    }

    /**
     * Convert to file string for saving.
     *
     * @return File string
     */
    @Override
    public String toFileString() {
        return String.format("%s|%b|%s|%s", "D", isDone, desc, by);
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + desc + " (by: " + by + ")";
    }
}
