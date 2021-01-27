class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
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