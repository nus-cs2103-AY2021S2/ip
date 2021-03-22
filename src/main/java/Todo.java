public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    /**
     * Factory method to create Todo task based on input
     *
     * @param input User input
     * @return Todo object
     * @throws DukeException If parsing input fails
     */
    public static Todo parse(String input) throws DukeException {
        String[] arr = input.split("todo");
        if (arr.length < 2) {
            throw new DukeException("Todo description cannot be empty");
        }

        String body = arr[1].strip();
        if (!body.trim().isEmpty()) {
            return new Todo(body);
        } else {
            throw new DukeException("Todo description cannot be empty");
        }
    }

    /**
     * Convert to file string for saving.
     *
     * @return File string
     */
    @Override
    public String toFileString() {
        return String.format("%s|%b|%s", "T", isDone, desc);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + desc;
    }
}
