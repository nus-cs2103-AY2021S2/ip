class ToDoTask extends Task {

    public ToDoTask(String description, int id) {
        super(description, id);
    }

    public ToDoTask(String description, int id, int status) {
        super(description, id);
        super.isDone = status > 0;
    }

    @Override
    public String toString() {
        return "[T]" + super.checkBoxToString() + description;
    }
}