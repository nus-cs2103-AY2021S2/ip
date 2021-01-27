class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.taskName;
        } else {
            return "[T][ ] " + this.taskName;
        }
    }
}