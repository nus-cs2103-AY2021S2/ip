public class Task {
    private final String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if(isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
