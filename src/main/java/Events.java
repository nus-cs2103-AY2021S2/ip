public class Events extends Task {
    protected String by;
    Events(String name, String by) {
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
}
