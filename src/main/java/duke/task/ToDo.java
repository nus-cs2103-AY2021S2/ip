package duke.task;
import duke.dukeexception.DukeException;

public class ToDo extends Task {
    /**
     * Class constructor specifying name.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Class constructor specifying name and status.
     */
    public ToDo(String name, boolean isDone) {
        super(name);
        this.isDone = isDone;
    }

    /**
     * Adds a todo to taskList.
     *
     * @param count  the current count of tasks in the taskList.
     * @throws DukeException  If an input or output
     *                      exception occurred
     */
    @Override
    public String addTask(int count) throws DukeException {
        if (this.name.equals("todo")) {
            throw new DukeException("      OOPS!!! The description of a todo cannot be empty.");
        } else {
            return super.addTask(count);
        }
    }

    /**
     * Overrides toString methods.
     *
     * @return the string representation of a todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
