public class Task {
    private boolean completed;
    private String name;

    public Task(String n) {
        this.completed = false;
        this.name = n;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getName() {
        return name;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public String toString() {
        if (!this.isCompleted()) {
            return String.format("[ ] %s", this.getName());
        } else {
            return String.format("[X] %s", this.getName());
        }
    }

}
