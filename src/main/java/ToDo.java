/**
 * Class that can create a todo task object.
 */
public class ToDo extends Task {
    /**
     * Constructor that creates the todo task.
     * @param taskInfo the description of the todo task.
     */
    public ToDo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getData() {
        if (isDone == true) {
            return "T!@#1!@#" + taskInfo;
        } else {
            return "T!@#0!@#" + taskInfo;
        }
    }
}
