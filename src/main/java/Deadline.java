public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name, "D");
        this.by = by;
    }

    public Deadline(String input) throws DukeException {
        super(input.split(" /by ", 2)[0], "D");

        String[] split = input.split(" /by ", 2);
        if (split.length != 2) {
            throw new DukeException("Deadline command must follow the format: description /by time");
        }

        this.by = split[1];
    }

    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    public String toLog() {
        return super.toLog() + " | " + by;
    }
}
