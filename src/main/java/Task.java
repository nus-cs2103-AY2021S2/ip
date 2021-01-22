public class Task {
    public boolean done = false;
    public String eventName = "";

    public Task(boolean done, String eventName) {
        this.done = done;
        this.eventName = eventName;
    }
    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done == true) {
            return "[X] " + eventName;
        } else {
            return "[ ] " + eventName;
        }
    }
}
