class Todo extends Task {
    Todo(String t) {
        super(t);
    }

    Todo(String task, boolean completed) {
        super(task, completed);
    }

    public static Todo readTask(String input) {
        String[] list = input.split(", ", 3);
        Todo result = new Todo(list[2], Boolean.parseBoolean(list[1]));
        return result;
    }

    public String toCommand() {
        return this.getClass().toString() + ", " + this.getCompleted() + ", " + this.getTask();
    }

    @Override
    public String toString() {
        return "[T]" + this.completedBox() + this.getTask();
    }
}
