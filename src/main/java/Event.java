public class Event extends Task {
    private final String by;
    Event(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Return the saved Format of Events task
     * @return
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone() ? "1" : "0")
                + " | " + this.getTaskName() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[E]" + (this.done ? "[X] " : "[ ] ") + this.getTaskName() + " (at: " + this.by + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event e = (Event) obj;
            return this.getTaskName().equals(e.getTaskName()) && this.by.equals(e.by);
        }
        return false;
    }
}
