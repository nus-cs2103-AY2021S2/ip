public abstract class Task {
    private String tag;
    private boolean isComplete;
    private String message;

    protected Task(String tag, String message) {
        this.tag = tag;
        this.isComplete = false;
        this.message = message;
    }

    public void setCompletion(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String toMemString() {
        int status = this.isComplete ? 1 : 0;
        return this.tag + " | " + status + " | " + this.message;
    }

    @Override
    public String toString() {
        String status = this.isComplete ? "[X] " : "[ ] ";
        return "[" + this.tag + "]" + status + this.message;
    }
}
