public class Event extends Task {

    public Event(String i) {
        super("E", i);
    }

    public Event(String[] arr) {
        super("E", arr);
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
