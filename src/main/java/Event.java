/**
 * The Event class is a child class of the Task Class,
 * it specifies the task as an Event using [E]
 */
class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}

