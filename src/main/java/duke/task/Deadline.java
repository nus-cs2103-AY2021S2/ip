package duke.task;
import java.time.LocalDate;
import duke.dukeException.DukeException;

public class Deadline extends Task{
    /** Date of the deadline */
    protected LocalDate by;

    /**
     * Class constructor specifying deadline name and due date.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Class constructor specifying deadline name, due date and status.
     */
    public Deadline(String name, LocalDate by, boolean isDone) {
        super(name);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Adds a deadline to taskList.
     *
     * @param count  the current count of tasks in the taskList.
     * @throws DukeException  If an input or output
     *                      exception occurred
     */
    @Override
    public void addTask(int count) throws DukeException {
        if (this.name.equals("deadline")) {
            throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            super.addTask(count);
        }
    }

    /**
     * Overrides toString method.
     *
     * @return the string representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getMonth() + " " + by.getDayOfMonth()+ " "+ by.getYear() + ")";
    }
}

