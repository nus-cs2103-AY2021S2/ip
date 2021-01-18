public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void done() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        if(isDone) {
            return "[ ]";
        } else {
            return "[X]";
        }
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }
}
