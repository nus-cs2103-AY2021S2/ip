public class Deadline extends Task {
    public Deadline(boolean isDone, String description) {
        super("D", isDone, description);
    }

    public String modifiedDescription() {
        String dateTime = description.split("/by ")[1];
        dateTime = "(by: " + dateTime + ")";
        return description.substring(0, description.indexOf('/')) + dateTime;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + modifiedDescription();
    }
}
