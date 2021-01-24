public class EventTask extends Task {
    String at;
    public EventTask(String description, boolean isDone, String at) {
        super(description);
        super.isDone = false;
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at:" + this.at + " )";
    }
    @Override
    public String getTaskDetails() {
        String divider = " | ";
        return "E" +  divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + at;
    }
}
