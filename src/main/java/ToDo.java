public class ToDo extends Task {

    public ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    public ToDo(String title) {
        this(title, false);
    }

    /**
     * @return a string describing the todo task
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
