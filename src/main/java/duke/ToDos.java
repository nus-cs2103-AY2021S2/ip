package duke;

public class ToDos extends Task {

    public ToDos(String name) {
        super(name);
    }

    public ToDos(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
