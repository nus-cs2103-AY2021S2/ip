public class EventTask extends Task {

    private String timeWindow;

    public EventTask(String taskDescription, String timeWindow) {
        this.taskDescription = taskDescription;
        this.timeWindow = timeWindow;
        this.taskType = 'E';
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[%c] [X] %s (at: %s)", this.taskType, this.taskDescription, this.timeWindow);
        } else {
            return String.format("[%c] [ ] %s (at: %s)", this.taskType, this.taskDescription, this.timeWindow);
        }
    }
}
