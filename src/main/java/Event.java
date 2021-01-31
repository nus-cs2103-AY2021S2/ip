public class Event extends Task {
    
    protected final String dateTime;

    /**
     * Factory method to create a new Event.
     * @param input - Input for the Event in the form "{ name } /at { date }".
     * @return - New Event object.
     */
    public static Event makeEvent(String input) throws ChecklstException {
        String[] splitInput = input.split(" /at ");
        if (splitInput.length == 1) {
            throw new ChecklstException("Inproper Event format used! Please use { name } /at { event }");
        }

        return new Event(splitInput[0], splitInput[1]);
    }

    protected Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    protected Event(String name, boolean completed, String dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
    }

    @Override
    public Task complete() {
        return new Event(this.name, true, this.dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime;
    }

}
