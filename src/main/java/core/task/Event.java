package core.task;

import core.task.Task;

public class Event extends Task {
    private String atTime;

    public Event(String desc) {
        super(desc);

        var parts = desc.split("/at");
        this.taskDescription = parts[0].trim();
        this.atTime = parts[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + atTime + ")";
    }
}
