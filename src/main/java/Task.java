public abstract class Task {
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

    abstract String getSymbol();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getSymbol(), this.getStatus(), this.getName());
    }
}
