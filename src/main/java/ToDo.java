public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public ToDo finishTask() {
        return new ToDo(description, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}