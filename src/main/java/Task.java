class Task {
    protected final String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void completeTask() {
        this.isDone = true;
    }

    public boolean isComplete() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return taskName;
    }
}