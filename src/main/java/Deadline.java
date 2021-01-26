public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String input) throws DukeException {
        super(input.split(" /by ", 2)[0]);

        String[] split = input.split(" /by ", 2);
        if (split.length != 2) {
            throw new DukeException("Deadline command must follow the format: description /by time");
        }

        this.by = split[1];
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
