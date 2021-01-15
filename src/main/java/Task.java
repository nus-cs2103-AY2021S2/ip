abstract class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public void markAsDone() {
        this.done = true;
    }
}
