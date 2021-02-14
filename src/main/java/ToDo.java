/**
 * The ToDo class is a child class of the Task Class,
 * it specifies the task as a ToDo using [T]
 */
class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}