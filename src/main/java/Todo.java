public class Todo extends AbstractTask {

    public Todo(String description) throws DukeEmptyDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}