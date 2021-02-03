public class Event extends Task {
    Event(String description) throws EmptyArgumentException {
        super(description);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
