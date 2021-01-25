package duke.exceptions;

/**
 * Represent the exception when the TaskList is empty but user wants to delete a task.
 */
public class DukeEmptyListException extends DukeException{

    /**
     * Return string representation for the cause of the DukeEmptyListException.
     * @return string representation for the cause of the exception.
     */
    @Override
    public String toString() {
        return "Cannot delete task in empty list.";
    }
}
