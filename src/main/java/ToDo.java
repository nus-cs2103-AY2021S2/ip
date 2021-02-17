/**
 * Class that can create a todo task object.
 */
public class ToDo extends Task {
    private static final String TODO_DISPLAY_ICON = "[T]";
    public static final String TODO_DATA_ICON = "T";

    /**
     * Constructor that creates the todo task.
     * @param taskInfo the description of the todo task.
     */
    public ToDo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public String toString() {
        return TODO_DISPLAY_ICON + super.toString();
    }

    public String getData() {
        if (isDone) {
            return TODO_DATA_ICON + DELIMITER + IS_DONE_TRUE_DATA_ICON + DELIMITER + taskInfo;
        } else {
            return TODO_DATA_ICON + DELIMITER + IS_DONE_FALSE_DATA_ICON + DELIMITER + taskInfo;
        }
    }
}
