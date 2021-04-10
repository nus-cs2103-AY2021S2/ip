package duke.commands;

public class ToDo extends Task {
    public ToDo(String toDoDetail) {
        super(toDoDetail);
    }

    @Override
    public String toString() {
        return "[T]" + " | " + super.toString();
    }
}
