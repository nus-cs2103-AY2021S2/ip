public class Deadline extends Task {
    Deadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
