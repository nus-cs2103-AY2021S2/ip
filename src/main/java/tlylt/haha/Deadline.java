package tlylt.haha;

public class Deadline extends Task {
    public Deadline(boolean isDone, String description) {
        super("D", isDone, description, Parser.getDate(description), Parser.getTime(description));
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + modifiedDescription("by");
    }
}
