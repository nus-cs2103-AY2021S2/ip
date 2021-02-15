public class ToDo extends Task {

    public ToDo(String description, String type) {
        super(description, type);
    }

    /**
     * Converts the object to a string.
     *
     * @return String containing details about the Task.
     */
    @Override
    public String toString() {
        String result = "[T]" + super.toString();
        return priority != null
                ? result + " " + "[" + priority.toString() + "]"
                : result;
    }
}
