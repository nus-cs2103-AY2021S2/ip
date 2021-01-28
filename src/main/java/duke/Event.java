package duke;

public class Event extends Task {

    public Event(String input) {
        super("E", input);
    }

    public Event(String[] array) {
        super("E", array);
    }

    @Override
    public String toString() {
        if (this.getDate().equals(" ")) {
            return super.toString();
        } else {
            return String.format("%s (at: %s)", super.toString(), this.getDate());
        }
    }

}
