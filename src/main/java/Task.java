public class Task {
    private boolean isDone;
    private final String name;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    public String getName() {
        return this.name;
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toSavedString() {
        return String.format(
                "%d | %s",
                this.getIsDone() ? 1 : 0,
                this.getName()
        );
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getIsDone()? "X" : " ", this.getName());
    }
}
