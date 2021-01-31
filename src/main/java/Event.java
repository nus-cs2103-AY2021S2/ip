public class Event extends Task {
    
    protected final String dateTime;

    /**
     * Factory method to create a new Event.
     * @param input - Input for the Event in the form "{ name } /at { date }".
     * @return - New Event object.
     */
    public static Event makeEvent(String input) {
        String[] splitInput = input.split(" /at ");
        if (splitInput.length == 1) {
            // throw exception
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
