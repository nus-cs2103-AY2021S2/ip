package duke.task;

/**
 * Class containing the relevant information about an Event.
 */
public class ToDo extends Task {
    /**
     * ToDo constructor.
     *
     * @param content ToDo description
     */
    public ToDo(String content) {
        super(content);
    }

    /**
     * String representation of the ToDo.
     * @return string representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * String representation of the ToDo when it is stored in a file.
     * @return file string representation of the ToDo
     */
    @Override
    public String toFileString() {
        String string = "T|" + super.toFileString();
        return string;
    }
}
