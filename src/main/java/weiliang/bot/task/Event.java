package weiliang.bot.task;

public class Event extends Task {
    
    private String timing;

    public Event(String task, String timing) {
        super(task);
        this.timing = timing;
    }

    @Override
    public String toFormattedString() {
        return "E | " + (completed ? 1 : 0) + " | " + task + " | " + timing;
    }

    @Override
    public String toString() {
        return "[E][" + (completed ? "X" : " ") + "] " + task + " (at: " + timing + ")";
    }
    
}
