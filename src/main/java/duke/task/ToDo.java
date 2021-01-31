package duke.task;
import duke.dukeException.DukeException;

public class ToDo extends Task{
    /**
     * Class constructor specifying name.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Class constructor specifying name and status.
     */
    public ToDo(String name, boolean done) {
        super(name);
        this.done = done;
    }

    /**
     * Adds a todo to taskList.
     *
     * @param count  the current count of tasks in the taskList.
     * @throws DukeException  If an input or output
     *                      exception occurred
     */
    @Override
    public void addTask(int count) throws DukeException{
        if(this.name.equals("todo")) {
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            super.addTask(count);
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
