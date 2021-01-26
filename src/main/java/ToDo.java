public class ToDo extends Task {
    public ToDo(String name) throws DukeException {
        super(name, "T");
        if (name.isBlank()) {
            throw new DukeException("ToDo description cannot be blank!");
        }
    }
}
