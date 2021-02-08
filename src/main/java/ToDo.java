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
        String result = "[T]" + super.toString();
        System.out.println(priority);
        return priority != null
                ? result + " " + "[" + priority.toString() + "]"
                : result;
    }
}
