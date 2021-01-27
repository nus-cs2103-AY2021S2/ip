package jeff;

public abstract class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public abstract String getSymbol();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getSymbol(), this.getStatus(), this.getName());
    }
}
