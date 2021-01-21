public class Task {
    private String message;
    private boolean complete;

    public Task(String message) {
        this.message = message;
        complete = false;
    }

    public void setCompletion(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        String status = complete ? "[X] " : "[ ] ";
        return status + message;
    }
}
