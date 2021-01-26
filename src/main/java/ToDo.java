public class ToDo extends Task {

    public ToDo(String description, String type) {
        super(description, type);
    }

    /**
     * converts the object to a string
     *
     * @return String containing details about the Task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
