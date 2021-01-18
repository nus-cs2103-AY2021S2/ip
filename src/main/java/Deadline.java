public class Deadline extends Task {
    String dueDate;

    public static Deadline createDeadline(String input) {
        String[] details = input.split(" /by ");
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
