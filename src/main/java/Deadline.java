public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, boolean b) {
        super(description,b);
    }

    public String toString() {
        return "[D]" + super.toString();
    }
}
