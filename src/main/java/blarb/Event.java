package blarb;

/**
 * {@code Event} is a {@code Task} that has a specific time of occurrence.
 *
 * @see Task
 */
class Event extends Task {
    private String at;

    /**
     * Constructs a new uncompleted {@code Event}.
     *
     * @param description The name of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void update(String description) {
        try {
            String[] fragments = description.split(" /at ");
            super.update(fragments[0]);
            this.at = fragments[1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParsingException("Type the event, then give the time using \"/at\".");
        }
    }

    /**
     * Converts {@code Event} into string format to be stored in file.
     *
     * @return String format to be stored in file.
     */
    public String encode() {
        return String.format("E / %s / %s", super.encode(), at);
    }

    /**
     * String representation of the Event.
     *
     * @return Event in check list form.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
