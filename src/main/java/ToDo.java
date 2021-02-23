public class ToDo extends Task {
    /**
     * Constructor method.
     * @param description User input description of ToDo
     */
    public ToDo(String description) {
        super(description);
    }
    /**
     * Method to return a ToDo object in the specified format
     * @return Formatted String of ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}