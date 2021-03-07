package duke.tasks;

/**
 * class ToDo
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent a todo in the task list
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * saveFormat: creates a formatted representation of a ToDo to be saved
     * @return representation of a ToDo to be saved
     */
    @Override
    public String saveFormat() {
        return "T | " + super.saveFormat();
    }

    /**
     * toString: creates a String representation of a ToDo
     * @return String representation of a ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
