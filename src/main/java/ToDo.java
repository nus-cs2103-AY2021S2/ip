public class ToDo extends Task {
    public ToDo(String name) throws DukeToDoException {
        super(name);
        if (name.isBlank()) {
            throw new DukeToDoException();
        }
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
