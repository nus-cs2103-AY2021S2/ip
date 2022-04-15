package kelbot;

public class TodoTask extends Task {
    /**
     * Initializes TodoTask.
     * @param name The name of the TodoTask.
     */
    public TodoTask(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
