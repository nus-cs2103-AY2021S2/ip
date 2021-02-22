package jeff.task;

/**
 * Represents a Task.
 * Parent class for Deadline, Event and ToDo.
 * Contains the name of the Task and whether it is done.
 */
public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param name Name of Task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Set Task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns Task name.
     *
     * @return Name of Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns status symbol for Task.
     * @return "X" if Task is done, " " otherwise.
     */
    public String getStatus() {
        return (isDone ? "X" : "  ");
    }

    /**
     * Returns symbol representing Task child class.
     *
     * @return Symbol for Task child class.
     */
    public abstract String getSymbol();

    /**
     * Returns String representation of Task.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getSymbol(), this.getStatus(), this.getName());
    }
}
