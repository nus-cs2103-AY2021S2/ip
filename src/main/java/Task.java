public class Task {
    private final String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String getContent() {
        return this.content;
    }

    public void markDone() {
        this.isDone = true;
    }
}
