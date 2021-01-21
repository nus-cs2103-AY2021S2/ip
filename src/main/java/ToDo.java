public class ToDo extends Task {

    public ToDo(String title) {
        super(title);
    }

    /**
     * @return a string describing the todo task
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
