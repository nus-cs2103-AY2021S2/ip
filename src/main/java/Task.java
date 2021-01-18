public class Task {
    private String task;
    private boolean done = false;

    public Task(String s, int i) {
        this.task = s;
    }

    public String getTask() {
        return this.task;
    }
    public void markDone() {
        this.done = true;
    }

    public boolean checkDone() {
        return this.done;
    }
}
