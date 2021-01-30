public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected final String icon;

    protected Task(String description, String icon) {
        this.description = description;
        this.icon = icon;
        isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String toString() {
        String doneIcon = isDone ? "[X]" : "[ ]";
        return "[" + icon + "]" + doneIcon + " " + this.description;
    }

    public String toLog() {
        String doneIcon = isDone ? "T" : "F";
        return icon + " | " + doneIcon + " | " + this.description;
    }

    public String getDescription() {
        return description;
    }
}
