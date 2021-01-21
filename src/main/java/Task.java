public class Task extends AbstractTask{

    public Task(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("   %s", super.toString());
    }
}