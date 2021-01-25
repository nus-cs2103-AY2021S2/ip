package pason.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
