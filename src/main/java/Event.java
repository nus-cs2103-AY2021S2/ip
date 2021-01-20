public class Event extends Task {

    private String timeWindow;

    public Event(String taskDescription, String timeWindow) {
        this.taskDescription = taskDescription;
        this.timeWindow = timeWindow;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[E] [X] %s (at: %s)",this.taskDescription, this.timeWindow);
        } else {
            return String.format("[E] [ ] %s (at: %s)",this.taskDescription, this.timeWindow);
        }
    }
}
