public class ToDo extends Task {
    public ToDo(String name) throws DukeException {
        super(name);
        if (name.isBlank()) {
            throw new DukeException("ToDo description cannot be blank!");
        }
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
