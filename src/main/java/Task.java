public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected final String icon;

    protected Task(String name, String icon) {
        this.name = name;
        this.icon = icon;
        isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String doneIcon = isDone ? "[X]" : "[ ]";
        return "[" + icon + "]" + doneIcon + " " + this.name;
    }

    public String toLog() {
        String doneIcon = isDone ? "T" : "F";
        return icon + " | " + doneIcon + " | " + this.name;
    }
}
