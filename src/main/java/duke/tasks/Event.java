package duke.tasks;

public class Event extends Task{
    protected String at;

    public Event(String taskName, boolean isCompleted, String at) {
        super(taskName, isCompleted);
        this.at = at;
    }

    public String toStorageString() {
        return "[E] || "+ (this.isCompleted ? "1" : "0") + " || " + this.taskName + " || " + this.at + ")";

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
