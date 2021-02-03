public class Deadline extends Task {
    Deadline(String description) throws EmptyArgumentException {
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
