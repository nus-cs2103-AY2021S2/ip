enum Status {
    DONE,
    NOT_DONE
}

abstract class Task {
    protected String name;
    protected Status done;

    public Task(String name) {
        this.name = name;
        this.done = Status.NOT_DONE;
    }

    public String getName() {
        return this.name;
    }

    public void markAsDone() {
        this.done = Status.DONE;
    }
}
