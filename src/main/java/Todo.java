/**
 * Class Todo is an extension of the Task class.
 *
 * @version 28 Jan 2021
 * @author Zhang Peng
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