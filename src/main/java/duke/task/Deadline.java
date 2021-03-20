package duke.task;

import java.time.LocalDate;

import duke.dukeexception.DukeException;

/**
 * Deadline is the main entity we'll be using to define a deadline task.
 * <p>
 * Please see the {@link Task} class for true identity
 *
 * @author Ni Jiaying
 */
public class Deadline extends Task {
    /**
     * Date of the deadline
     */
    protected LocalDate doneBy;

    /**
     * Class constructor specifying deadline name and due date.
     */
    public Deadline(String name, LocalDate doneBy) {
        super(name);
        this.doneBy = doneBy;
    }

    /**
     * Class constructor specifying deadline name, due date and status.
     */
    public Deadline(String name, LocalDate doneBy, boolean isDone) {
        super(name);
        this.doneBy = doneBy;
        this.isDone = isDone;
    }

    public Character getType() {
        return 'D';
    }

    public LocalDate getDate() {
        return doneBy;
    }
    /**
     * Adds a deadline to taskList.
     *
     * @param count the current count of tasks in the taskList.
     * @throws DukeException If an input or output
     *                       exception occurred
     */
    @Override
    public String addTask(int count) throws DukeException {
        if (this.name.equals("deadline")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            return super.addTask(count);
        }
    }

    /**
     * Overrides toString method.
     *
     * @return the string representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + doneBy.getMonth() + " "
                + doneBy.getDayOfMonth() + " " + doneBy.getYear() + ")";
    }
}

