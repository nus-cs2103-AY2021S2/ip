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

    String getName() {
        return this.name;
    }

    String getStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.getName());
    }
}
