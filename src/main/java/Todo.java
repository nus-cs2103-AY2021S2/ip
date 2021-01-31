class Todo extends Task {
    public Todo(String description, String date, String time) {
        super(description, date, time, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}