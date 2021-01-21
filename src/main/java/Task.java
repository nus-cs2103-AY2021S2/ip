public abstract class Task {
    private String tag;
    private boolean complete;
    private String message;

    protected Task(String tag, String message) {
        this.tag = tag;
        this.complete = false;
        this.message = message;
    }

    public void setCompletion(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        String status = complete ? "[X] " : "[ ] ";
        return "[" + tag + "]" + status + message;
    }
}
