class Todo extends Task {
    Todo(String t) {
        super(t);
    }

    Todo(String task, boolean completed) {
        super(task, completed);
    }

    public static Todo readTaskFromStorage(String input) {
        String[] list = input.split(", ", 3);
        assert list.length == 3 : "Todo was stored from previous user visit is corrupted.";
        Todo result = new Todo(list[2], Boolean.parseBoolean(list[1]));
        return result;
    }

    public String saveInStorageAs() {
        return this.getClass().toString() + ", " + this.getCompleted() + ", " + this.getTask();
    }

    @Override
    public String toString() {
        return "[T]" + this.completedBox() + this.getTask();
    }
}
