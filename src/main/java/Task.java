public class Task extends AbstractTask{

    public Task(String description) throws DukeEmptyDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("   %s", super.toString());
    }
}