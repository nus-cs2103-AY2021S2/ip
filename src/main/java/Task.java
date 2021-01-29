public class Task {
    public boolean isDone;
    public String eventName;
    public String eventType;

    public Task(boolean done, String eventName) {
        this.isDone = done;
        this.eventName = eventName;
        this.eventType = "T";
    }

    public Task(boolean done, String eventName, String eventType) {
        this.isDone = done;
        this.eventName = eventName;
        this.eventType = eventType;
    }
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.isDone == true) {
            return "[X] " + eventName;
        } else {
            return "[ ] " + eventName;
        }
    }
}
