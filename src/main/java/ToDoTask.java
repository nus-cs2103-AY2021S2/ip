class ToDoTask extends Task {

    public ToDoTask(String description, int id) {
        super(description, id);
    }

    @Override public String toString() {
        return super.toString() + " [T]" + super.checkBoxToString() + description;
    }
}