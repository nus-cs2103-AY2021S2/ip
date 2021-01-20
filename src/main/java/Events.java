public class Events extends Task {
    protected String description;
    protected boolean isDone;

    public Events(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
