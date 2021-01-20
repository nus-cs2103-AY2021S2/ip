public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    static public Todo parse(String input) throws DukeException {
        String body = input.split("todo")[1].strip();
        if (!body.trim().isEmpty()) {
            return new Todo(body);
        } else {
            throw new DukeException("Todo description cannot be empty");
        }
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + desc;
    }
}
