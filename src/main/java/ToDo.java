/*
 * ToDo class to handle tasks without any deadline (no date /time)
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo class
     * @param description details of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Displays task type and task description
     * @return String format of ToDO task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}