package tlylt.haha;

public class Event extends Task {
    public Event(boolean isDone, String description) {
        super("E", isDone, description, Parser.getDate(description), Parser.getTime(description));
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + modifiedDescription("at");
    }
}
