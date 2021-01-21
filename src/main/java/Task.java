public class Task {
    private String title;
    private String status;

    public Task(String title) {
        this.title = title;
        this.status = "undone";
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isDone() {
        return status.equals("done");
    }

    /**
     * mark the task as done
     */
    public void done() {
        this.status = "done";
    }

    /**
     * @return title of the task with its status
     */
    public String toString() {
        return "[" + (isDone() ? "X" : " ") + "] " + title;
    }
}
