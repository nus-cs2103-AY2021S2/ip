public class Event extends Task {
    private String timing;

    Event(String name, String timing) {
        super(name);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.timing + ")";
    }
}
