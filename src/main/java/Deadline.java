public class Deadline extends Task {
    String dueDate;

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

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
