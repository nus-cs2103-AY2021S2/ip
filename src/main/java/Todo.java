public class Todo extends Task {
    Todo(String name) throws DukeException {
        super(name);
        if (name.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }
}
