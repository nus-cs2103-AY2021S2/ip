/**
 * Class Todo is an extension of the Task class.
 * @author Zhang Peng.
 * @version 21 Jan 2021.
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * This is the toString() method of the class
     * @return String This returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
