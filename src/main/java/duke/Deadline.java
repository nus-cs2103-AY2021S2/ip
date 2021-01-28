package duke;

public class Deadline extends Task {

    public Deadline(String input) {
        super("D", input);
    }

    public Deadline(String[] arr) {
        super("D", arr);
    }

    @Override
    public String toString() {
        if (this.getDate().equals(" ")) {
            return super.toString();
        } else {
            return String.format("%s (by: %s)", super.toString(), this.getDate());
        }
    }

}
