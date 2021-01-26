class Todo extends Task {
    Todo(String t) {
        super(t);
    }

    @Override
    public String toString() {
        return "[T]" + this.completedBox() + this.task;
    }
}