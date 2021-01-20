public class Event extends Task {
    private String time;

    public Event(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E][" + (done ? "X" : " ") + "] " + taskDescription + " (" + time + ")";
    }
}
