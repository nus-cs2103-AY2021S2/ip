class ToDoTask extends Task {

    public ToDoTask(String description, int id) {
        super(description, id);
    }

    @Override
    public String toString() {
        return "[T]" + super.checkBoxToString() + description;
    }
}