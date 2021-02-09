/**
 * event class
 */
public class Event extends Task {
    /**
     *
     * @param description event description
     * @throws EmptyArgumentException empty argument exception
     */
    Event(String description) throws EmptyArgumentException {
        super(description);
    }

    /**
     *
     * @return string representation of the event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
