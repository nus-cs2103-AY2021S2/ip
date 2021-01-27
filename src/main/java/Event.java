/**
 * Represents an Event task. Consists of task to do, as well as
 * deadline of the task.
 */
public class Event extends Task{
    private final String deadline;

    /**
     * Constructor of an Event object
     * @param task Task need to be done
     * @param deadline Deadline when to be done
     */
    Event(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Constructor of an Event object
     * @param task Task need to be done
     * @param deadline Deadline when to be done
     * @param done Boolean representing whether the task has been done or not
     */
    Event(String task, String deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;
    }

    /**
     * An overriden method from parent class Task. The purpose is to marked the
     * current Event as done.
     * @return A new Event that has been done
     */
    @Override
    public Task finishTask() {
        return new Event(this.task, this.deadline, true);
    }

    /**
     * An overriden method from parent class Task. The purpose is to represents
     * a String for later saved in txt files
     * @return String representation in txt files
     */
    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + this.deadline;
    }

    /**
     * String representation of an Event class
     * @return String representation of an Event class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.deadline + ")";
    }
}
