package duke;

public class Task {
    public boolean isDone;
    public String eventName;
    public String eventType;

    public Task(boolean done, String eventName, String eventType) {
        this.isDone = done;
        this.eventName = eventName;
        this.eventType = eventType;
    }

    /**
     * This function sets a task to be done
     */
    public void setDone() {

        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.isDone == true) {
            return "[X] " + eventName + "\n";
        } else {
            return "[ ] " + eventName + "\n";
        }
    }
}
